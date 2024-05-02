package kz.alabs.vetclinic.pet.service.Impl;

import kz.alabs.vetclinic.core.exception.NoSuchElementFoundException;
import kz.alabs.vetclinic.pet.mapper.PetRequestMapper;
import kz.alabs.vetclinic.pet.mapper.PetResponseMapper;
import kz.alabs.vetclinic.pet.model.dto.PetRequest;
import kz.alabs.vetclinic.pet.model.dto.PetResponse;
import kz.alabs.vetclinic.pet.model.entity.Pet;
import kz.alabs.vetclinic.pet.repository.PetRepository;
import kz.alabs.vetclinic.pet.service.PetService;
import kz.alabs.vetclinic.type.model.dto.TypeSetRequest;
import kz.alabs.vetclinic.type.service.Impl.TypeServiceImpl;
import kz.alabs.vetclinic.user.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kz.alabs.vetclinic.core.util.Constants.NOT_FOUND_PET;
import static kz.alabs.vetclinic.core.util.Constants.NOT_FOUND_RECORD;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final TypeServiceImpl typeServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final PetRequestMapper petRequestMapper;
    private final PetResponseMapper petResponseMapper;

    @Override
    @Transactional
    public PetResponse create(PetRequest request) {
        final Pet pet = petRequestMapper.toEntity(request);
        pet.setType(typeServiceImpl.getById(request.getTypeId()));
        pet.setUser(userServiceImpl.getById(request.getUserId()));
        petRepository.save(pet);
        log.info("Pet has been created");
        return petResponseMapper.toDto(pet);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PetResponse> findAll(Pageable pageable) {
        final Page<PetResponse> pets = petRepository.findAll(pageable)
                .map(petResponseMapper::toDto);

        if (pets.isEmpty()) {
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }
        return pets;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> findAllByType(TypeSetRequest types) {
        return petRepository.findAll().stream()
                .filter(pet -> types.getIds().isEmpty() || types.getIds().contains(pet.getType().getId()))
                .collect(Collectors.groupingBy(x -> x.getType().getName(), Collectors.counting()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> findAllByUserId(long userId) {
        final List<PetResponse> pets = petRepository.findAllByUserId(userId).stream()
                .map(petResponseMapper::toDto).toList();

        if (pets.isEmpty()) {
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }
        return pets;
    }

    @Override
    @Transactional(readOnly = true)
    public PetResponse findById(long id) {
        return petRepository.findById(id)
                .map(petResponseMapper::toDto)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_PET));
    }

    @Override
    @Transactional
    public PetResponse update(PetRequest request) {
        if (!petRepository.existsById(request.getId())) {
            throw new NoSuchElementFoundException(NOT_FOUND_PET);
        }
        final Pet pet = petRequestMapper.toEntity(request);
        pet.setType(typeServiceImpl.getById(request.getTypeId()));
        pet.setUser(userServiceImpl.getById(request.getUserId()));
        petRepository.save(pet);
        log.info("Pet has been updated");
        return petResponseMapper.toDto(pet);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        final Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_PET));
        petRepository.delete(pet);
        log.info("Pet has been deleted");
    }

}
