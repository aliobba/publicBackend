package com.ooredoo.report_builder.entity;


import jakarta.persistence.*;

@Entity
public class SubmissionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id", nullable = false)
    private FormSubmission submission;

    // FIXED: Link to FormComponentAssignment instead of FormComponent
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private FormComponentAssignment assignment;

    @Column(columnDefinition = "TEXT")
    private String value;

    public SubmissionValue(Integer id, String value, FormSubmission submission, FormComponentAssignment assignment) {
        this.id = id;
        this.value = value;
        this.submission = submission;
        this.assignment = assignment;
    }

    public SubmissionValue() {
    }

    // Helper method to get component (for backward compatibility)
    public FormComponent getComponent() {
        return assignment != null ? assignment.getComponent() : null;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FormSubmission getSubmission() {
        return this.submission;
    }

    public void setSubmission(FormSubmission submission) {
        this.submission = submission;
    }

    public FormComponentAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(FormComponentAssignment assignment) {
        this.assignment = assignment;
    }

}
