package com.ooredoo.report_builder.entity;


import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "form_submission")
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
    private LocalDateTime submittedDate;

    @Column(name = "is_complete")
    private Boolean isComplete = true;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubmissionValue> values;

    public FormSubmission(Integer id, LocalDateTime submittedAt, Form form, User submittedBy, List<SubmissionValue> values) {
        this.id = id;
        this.submittedDate = submittedAt;
        this.form = form;
        this.submittedBy = submittedBy;
        this.values = values;
    }

    public FormSubmission(List<SubmissionValue> values, Boolean isComplete, LocalDateTime submittedAt, User submittedBy, Form form) {
        this.values = values;
        this.isComplete = isComplete;
        this.submittedDate = submittedAt;
        this.submittedBy = submittedBy;
        this.form = form;
    }

    public FormSubmission(Form form, User submittedBy) {
        this.form = form;
        this.submittedBy = submittedBy;
        this.submittedDate = LocalDateTime.now();
    }

    public FormSubmission(Integer id, Form form, User submittedBy, LocalDateTime submittedDate, Boolean isComplete, List<SubmissionValue> values) {
        this.id = id;
        this.form = form;
        this.submittedBy = submittedBy;
        this.submittedDate = submittedDate;
        this.isComplete = isComplete;
        this.values = values;
    }

    public FormSubmission() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Form getForm() {
        return this.form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public User getSubmittedBy() {
        return this.submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    public LocalDateTime getSubmittedDate() {
        return this.submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submitted_Date) {
        this.submittedDate = submitted_Date;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public List<SubmissionValue> getValues() {
        return this.values;
    }

    public void setValues(List<SubmissionValue> values) {
        this.values = values;
    }
}
