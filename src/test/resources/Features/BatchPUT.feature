@tag2
Feature: Batch module API testing

  @tag2
  Scenario Outline: Check if user able to update existing Batch with valid values
    Given User creates new batch for this test and gets batch details from "<Sheetname>" and "<batchNamePrefix>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    When User sends HTTPS PUT Request and request Body with all field from "<Sheetname>" and "<newBatchDesc>"
    Then User Receives 200 status code with response body

    Examples: 
      | Sheetname | batchNamePrefix | batchDesc        | batchStatus | batchNoOfClass   | programId | newBatchDesc        |
      | Batch     | BatchNamePrefix | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID | newBatchDescription | 
  
  @tag2
  Scenario Outline: Check if user able to update a Batch with missing values
   	Given User creates new batch for this test and gets batch details from "<Sheetname>" and "<batchNamePrefix>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    When User sends HTTPS PUT Request with missing fields in request Body
    Then Verify put batches API returns 400 status code and with batch missing fields error

    Examples: 
      | Sheetname | batchNamePrefix | batchDesc        | batchStatus | batchNoOfClass   | programId | 
      | Batch     | BatchNamePrefix | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID | 
      
      
  @tag2
  Scenario Outline: Check if user able to update a Batch with invalid batchid
   	Given User creates new batch for this test and gets batch details from "<Sheetname>" and "<batchNamePrefix>" and "<batchDesc>" and "<batchStatus>" and "<batchNoOfClass>" and "<programId>"
    When User sends HTTPS PUT Request with invalid batchid in request Body
    Then Verify put batches API returns 404 status code and not found

    Examples: 
      | Sheetname | batchNamePrefix | batchDesc        | batchStatus | batchNoOfClass   | programId | 
      | Batch     | BatchNamePrefix | BatchDescription | BatchStatus | BatchNoOfClasses | ProgramID | 