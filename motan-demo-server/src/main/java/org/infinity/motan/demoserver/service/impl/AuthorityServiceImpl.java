package org.infinity.motan.demoserver.service.impl;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import org.infinity.motan.democommon.domain.Authority;
import org.infinity.motan.democommon.service.AuthorityService;
import org.infinity.motan.demoserver.repository.AuthorityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MotanService
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<String> findAllAuthorityNames(Boolean enabled) {
        return authorityRepository.findByEnabled(enabled).stream().map(Authority::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAuthorityNames() {
        return authorityRepository.findAll().stream().map(Authority::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Authority> findAll(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Optional<Authority> findById(String id) {
        return authorityRepository.findById(id);
    }

    @Override
    public void save(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public void deleteById(String id) {
        authorityRepository.deleteById(id);
    }
}