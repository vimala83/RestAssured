  @tag2
Feature: Batch module API testing

  @tag2
  Scenario Outline: Check if user able delete existing Batch
    Given User creates new batch for this delete test and gets batch details from "<Sheetname>" and "<batchNamePrefix>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    When User sends HTTPS DELETE Request for same batch
    Then User Receives 200 status code for delete

    Examples: 
      | Sheetname | batchNamePrefix | batchDesc        | batchStatus | batchNoOfClass   | programId | newBatchDesc        |
      | Batch     | BatchNamePrefix | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID | newBatchDescription | 
      
  @tag2
  Scenario Outline: Check if user able delete with invalid batchid
    Given User creates new batch for this delete test and gets batch details from "<Sheetname>" and "<batchNamePrefix>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    When User sends HTTPS DELETE Request for invalid batchid
    Then User Receives 404 status code for delete

    Examples: 
      | Sheetname | batchNamePrefix | batchDesc        | batchStatus | batchNoOfClass   | programId | newBatchDesc        |
      | Batch     | BatchNamePrefix | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID | newBatchDescription | 
 