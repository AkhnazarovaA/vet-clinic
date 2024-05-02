package kz.alabs.vetclinic.user.service;

import kz.alabs.vetclinic.user.model.dto.ProfileRequest;
import kz.alabs.vetclinic.user.model.dto.UserRequest;
import kz.alabs.vetclinic.user.model.dto.UserResponse;
import kz.alabs.vetclinic.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse create(UserRequest request);

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse findById(long id);

    User getById(long id);

    UserResponse update(ProfileRequest request);

    void deleteById(long id);

}
