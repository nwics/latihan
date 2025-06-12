package com.example.latihan.dto;

import com.example.latihan.model.PermissionType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {
    private Long permissionId;

    @NotNull(message = "Permission type is required")
    private PermissionType permissionType;

    private Long roleId;
}
