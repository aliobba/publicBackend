package com.ooredoo.report_builder.entity;


import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "form_submissions")
public class FormSubmission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne
    @JoinColumn(name = "submitted_by_id")
    private User submittedBy;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private List<SubmissionValue> values;

    public FormSubmission(Integer id, LocalDateTime submittedAt, Form form, User submittedBy, List<SubmissionValue> values) {
        this.id = id;
        this.submittedAt = submittedAt;
        this.form = form;
        this.submittedBy = submittedBy;
        this.values = values;
    }

    public FormSubmission() {
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
