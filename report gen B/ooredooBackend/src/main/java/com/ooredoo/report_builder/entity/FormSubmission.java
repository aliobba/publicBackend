package com.ooredoo.report_builder.entity;


import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "form_submissions")
public class FormSubmission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitted_by_id", nullable = false)
    private User submittedBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    @Column(name = "is_complete")
    private Boolean isComplete = true;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubmissionValue> values;

    public FormSubmission(Integer id, LocalDateTime submittedAt, Form form, User submittedBy, List<SubmissionValue> values) {
        this.id = id;
        this.submittedAt = submittedAt;
        this.form = form;
        this.submittedBy = submittedBy;
        this.values = values;
    }

    public FormSubmission(List<SubmissionValue> values, Boolean isComplete, LocalDateTime submittedAt, User submittedBy, Form form) {
        this.values = values;
        this.isComplete = isComplete;
        this.submittedAt = submittedAt;
        this.submittedBy = submittedBy;
        this.form = form;
    }

    public FormSubmission(Form form, User submittedBy) {
        this.form = form;
        this.submittedBy = submittedBy;
        this.submittedAt = LocalDateTime.now();
    }

    public FormSubmission() {
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Integer getId() {
        return this.id;
    }

    public LocalDateTime getSubmittedAt() {
        return this.submittedAt;
    }

    public Form getForm() {
        return this.form;
    }

    public User getSubmittedBy() {
        return this.submittedBy;
    }

    public List<SubmissionValue> getValues() {
        return this.values;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    public void setValues(List<SubmissionValue> values) {
        this.values = values;
    }
}
