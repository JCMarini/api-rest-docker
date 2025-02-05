package com.jcmc.demo.auth.controller;

import com.jcmc.demo.auth.dao.UserRepository;
import com.jcmc.demo.auth.entity.UserResponse;
import com.jcmc.demo.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserRepository userRepository;

    //Se utiliza la cache de redis para .
//    @Cacheable("findAll")
    @GetMapping("list")
    public ResponseEntity<Page<UserResponse>> findAll(@PageableDefault(value = 2, page = 0) Pageable pageable) {
        List<UserResponse> usuarios = userRepository.findAll(pageable).stream()
                .map(user -> new UserResponse(user.getName(), user.getEmail())).toList();

        return ResponseEntity.ok( toPage(usuarios, pageable));
    }

// metodo para regresar listas paginadas.
    public static <T> Page<T> toPage(List<T> lista, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), lista.size());
        List<T> sublist = lista.subList(start, end);
        return new PageImpl<>(sublist, pageable, lista.size());
    }
}
