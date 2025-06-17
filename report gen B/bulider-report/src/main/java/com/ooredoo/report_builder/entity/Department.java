package com.ooredoo.report_builder.entity;

import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@EntityListeners(AuditingEntityListener.class)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Department admin/manager
    @OneToOne
    @JoinColumn(name = "admin_id")
    private User departmentAdmin;

    // Users belonging to this department
    @OneToMany(mappedBy = "department")
    private Set<User> users = new HashSet<>();

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public Department(Integer id, String name, String description, User departmentAdmin, Set<User> users, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentAdmin = departmentAdmin;
        this.users = users;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Department() {
    }

    protected Department(DepartmentBuilder<?, ?> b) {
        this.id = b.id;
        this.name = b.name;
        this.description = b.description;
        this.departmentAdmin = b.departmentAdmin;
        this.users = b.users;
        this.createdAt = b.createdAt;
        this.updatedAt = b.updatedAt;
    }

    public static DepartmentBuilder<?, ?> builder() {
        return new DepartmentBuilderImpl();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public User getDepartmentAdmin() {
        return this.departmentAdmin;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartmentAdmin(User departmentAdmin) {
        this.departmentAdmin = departmentAdmin;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static abstract class DepartmentBuilder<C extends Department, B extends DepartmentBuilder<C, B>> {
        private Integer id;
        private String name;
        private String description;
        private User departmentAdmin;
        private Set<User> users;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public B id(Integer id) {
            this.id = id;
            return self();
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B description(String description) {
            this.description = description;
            return self();
        }

        public B departmentAdmin(User departmentAdmin) {
            this.departmentAdmin = departmentAdmin;
            return self();
        }

        public B users(Set<User> users) {
            this.users = users;
            return self();
        }

        public B createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return self();
        }

        public B updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "Department.DepartmentBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", departmentAdmin=" + this.departmentAdmin + ", users=" + this.users + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }

    private static final class DepartmentBuilderImpl extends DepartmentBuilder<Department, DepartmentBuilderImpl> {
        private DepartmentBuilderImpl() {
        }

        protected DepartmentBuilderImpl self() {
            return this;
        }

        public Department build() {
            return new Department(this);
        }
    }
}