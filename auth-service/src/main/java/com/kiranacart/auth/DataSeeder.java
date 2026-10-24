package com.kiranacart.auth;

import com.kiranacart.auth.entity.Permission;
import com.kiranacart.auth.entity.Role;
import com.kiranacart.auth.repository.PermissionRepository;
import com.kiranacart.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void run(String... args){
        if(roleRepository.count() > 0){
            return;
        }

        Permission refundRequest = permissionRepository.save(Permission.builder().code("REFUND_REQUEST").build());
        Permission refundViewAll = permissionRepository.save(Permission.builder().code("REFUND_VIEW_ALL").build());
        Permission refundApprove = permissionRepository.save(Permission.builder().code("REFUND_APPROVE").build());
        Permission complaintCreate = permissionRepository.save(Permission.builder().code("COMPLAINT_CREATE").build());
        Permission complaintViewAll = permissionRepository.save(Permission.builder().code("COMPLAINT_VIEW_ALL").build());
        Permission complaintResolve = permissionRepository.save(Permission.builder().code("COMPLAINT_RESOLVE").build());

        roleRepository.save(Role.builder()
                .name("CUSTOMER")
                .permissions(Set.of(refundRequest, complaintCreate))
                        .build());

        roleRepository.save(Role.builder()
                .name("REFUND_MANAGER")
                .permissions(Set.of(refundViewAll, refundApprove))
                .build());

        roleRepository.save(Role.builder()
                .name("SUPPORT_AGENT")
                .permissions(Set.of(complaintViewAll, complaintResolve))
                .build());

        roleRepository.save(Role.builder()
                .name("ADMIN")
                .permissions(Set.of(refundRequest, refundViewAll, refundApprove, complaintCreate, complaintViewAll, complaintResolve))
                .build());
        System.out.println(">>> Seeded roles and permissions successfully");
    }
}
