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
