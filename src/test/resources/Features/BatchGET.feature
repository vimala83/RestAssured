@BatchGet
Feature: Batch module API testing

  @BatchGet_001
  Scenario: Verify GetAll Batches response retuns valid status code and schema
    Given Get all batch module url is set with valid url
    When User sends GET All batches request
    Then Make sure batches api returns success 200 status code and verify collection schema

  @BatchGet_002
  Scenario Outline: Verify Get Batche by BatchID with valid BatchID
    Given User sets request for Batch module with valid base URL and valid path
    When User sends GET request with valid batchID from "<Sheetname>" and "<Testcase>"
    Then Make sure batches api returns single batch success 200 status code and verify schema

    Examples: 
      | Sheetname | Testcase |
      | Batch     | BatchID  |

  @BatchGet_003
  Scenario: Verify Get Batches By BatchID for invalid BatchID
    Given User sets request for Batch module with valid base URL and valid path
    When User sends GET request with invalid batchID
    Then Verify batches API returns 404 status code and with message not found

  @BatchGet_004
  Scenario Outline: Verify Get Batches by BatchName with valid BatchName
    Given User sets request for BatchName in Batch module with valid base URL and valid Path
    When User sends GET request with valid batchName from "<Sheetname>" and "<Testcase>"
    Then Verify batches api returns single batchname success 200 status code and verify schema

    Examples: 
      | Sheetname | Testcase          |
      | Batch     | BatchName |

  @BatchGet_005
  Scenario: Verify Get Batches By BatchName for invalid BatchName
    Given User sets request for Batch module with valid base URL and valid path
    When User sends GET request with invalid batchName
    Then Verify batches API returns 404 status code and with message not found

  @BatchGet_006
  Scenario Outline: Verify Get Batches with valid ProgramId
    Given User sets request for ProgramId in Batch module with valid base URL and valid Path
    When User sends GET request with valid ProgramId from "<Sheetname>" and "<Testcase>"
    Then Verify batches api returns single batchname success 200 status code and verify schema

    Examples: 
      | Sheetname | Testcase  |
      | Batch     | ProgramID |
      
  @BatchGet_007
  Scenario: Verify Get Batches for invalid invalidProgramId
    Given User sets request for Batch module with valid base URL and valid path
    When User sends GET request with invalid ProgramId
    Then Verify batches API returns 404 status code and with message not found    
      
      
      
