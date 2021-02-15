package org.infinity.motan.democommon.service;

import org.infinity.motan.democommon.dto.AdminMenuTreeDTO;

import java.util.List;

public interface AdminMenuService {

    List<AdminMenuTreeDTO> getMenus();

}