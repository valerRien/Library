package com.example.newtry.security;

import com.example.newtry.models.Admin;
import com.example.newtry.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AdminDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminDetailService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findAdminByUsername(username);
        if (admin == null) throw new UsernameNotFoundException("%s не найден.".formatted(username));
        else return new AdminDetail(admin);
    }
}
