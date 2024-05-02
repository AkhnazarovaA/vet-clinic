package kz.alabs.vetclinic.type.service.Impl;

import kz.alabs.vetclinic.core.exception.ElementAlreadyExistsException;
import kz.alabs.vetclinic.core.exception.NoSuchElementFoundException;
import kz.alabs.vetclinic.type.mapper.TypeRequestMapper;
import kz.alabs.vetclinic.type.mapper.TypeResponseMapper;
import kz.alabs.vetclinic.type.model.dto.TypeRequest;
import kz.alabs.vetclinic.type.model.dto.TypeResponse;
import kz.alabs.vetclinic.type.model.entity.Type;
import kz.alabs.vetclinic.type.reposity.TypeRepository;
import kz.alabs.vetclinic.type.service.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kz.alabs.vetclinic.core.util.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeRequestMapper typeRequestMapper;
    private final TypeResponseMapper typeResponseMapper;

    @Override
    @Transactional
    public TypeResponse create(TypeRequest request) {
        if (typeRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_TYPE);
        }
        final Type type = typeRequestMapper.toEntity(request);
        typeRepository.save(type);
        log.info("Type has been created");
        return typeResponseMapper.toDto(type);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeResponse> findAll(Pageable pageable) {
        final Page<TypeResponse> types = typeRepository.findAll(pageable)
                .map(typeResponseMapper::toDto);

        if (types.isEmpty()) {
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }
        return types;
    }

    @Override
    @Transactional(readOnly = true)
    public TypeResponse findById(long id) {
        return typeRepository.findById(id)
                .map(typeResponseMapper::toDto)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_TYPE));
    }

    @Override
    @Transactional(readOnly = true)
    public Type getById(long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_TYPE));
    }

    @Override
    @Transactional
    public TypeResponse update(TypeRequest request) {
        final Type type = typeRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_TYPE));

        if (
            !request.getName().equalsIgnoreCase(type.getName())
            && typeRepository.existsByNameIgnoreCase(request.getName())
        ) {
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_TYPE);
        }

        typeRepository.save(typeRequestMapper.toEntity(request));
        log.info("Type has been updated");
        return typeResponseMapper.toDto(type);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        final Type type = typeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(NOT_FOUND_TYPE));
        typeRepository.delete(type);
        log.info("Type has been deleted");
    }

}
