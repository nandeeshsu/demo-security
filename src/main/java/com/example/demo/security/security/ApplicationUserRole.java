/**
 * 
 */
package com.example.demo.security.security;

import java.util.Set;

/**
 * @author nande
 *
 */
public enum ApplicationUserRole {
	STUDENT(Set.of()),
	ADMIN(Set.of(ApplicationUserPermission.COURSE_READ, 
			ApplicationUserPermission.COURSE_WRITE, 
			ApplicationUserPermission.STUDENT_READ, 
			ApplicationUserPermission.STUDENT_WRITE)),
	ADMIN_TRAINEE(Set.of(ApplicationUserPermission.COURSE_READ,
			ApplicationUserPermission.STUDENT_READ));
	
	private final Set<ApplicationUserPermission> permissions;
	
	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
}
