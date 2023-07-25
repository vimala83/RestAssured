@tag2
Feature: Batch module API testing

  @tag2
  Scenario Outline: Check if user able to create a Batch with valid endpoint and request body (non existing values)
    Given User creates POST Request for the LMS API endpoint
    When User sends HTTPS Request and  request Body with all field from "<Sheetname>" and "<batchName>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    Then User Receives 201 created status with response body

    Examples: 
      | Sheetname | batchName | batchDesc        | batchStatus | batchNoOfClass   | programId |
      | Batch     | BatchName | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID |
      
  @tag2
  Scenario Outline: Check if user able to create a Existing Batch values with valid endpoint and request body 
    Given User creates new batch and gets batch details from "<Sheetname>" and "<batchName>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    When User sends HTTPS Request with Existing batch details
    Then Verify batches API returns 400 status code and with batch already there message

    Examples: 
      | Sheetname | batchName | batchDesc        | batchStatus | batchNoOfClass   | programId |
      | Batch     | BatchName | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID |
      
  @tag2
  Scenario Outline: Check if user able to create a Batch with missing values
    Given User creates POST Request for the LMS API endpoint
    When User sends HTTPS Request and  request Body with missing fields from "<Sheetname>" and "<batchNamePrefix>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    Then Verify batches API returns 400 status code and with batch missing fields error

    Examples: 
      | Sheetname | batchNamePrefix | batchDesc        | batchStatus | batchNoOfClass   | programId |
      | Batch     | BatchNamePrefix | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID |