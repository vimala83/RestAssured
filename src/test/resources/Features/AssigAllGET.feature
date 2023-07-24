#Author: Diksha
@assignmentget
Feature: Assignment GET METHOD

  @Assignment_Get_01
  Scenario: Verify user able to retrieve a record for All Assignment
    Given User set GET Request for the LMS API endpoint
    When User set Https Request
    Then User "200" OK Status .

  @Assignment_Get_02
  Scenario Outline: Verify user able to retrieve a record with valid AssignmentID
    Given User send GET Request for the LMS API endpoint with valid Assignment ID
    When User create GET request with valid AssignmentID from "<Sheetname>" and "<Testcase>"
    Then User receives "200" OK Status with response body.

    Examples: 
      | Sheetname  | Testcase     |
      | Assignment | AssignmentID |

  @Assignment_Get_03
  Scenario Outline: Verify user able to retrieve a record with invalid AssignmentID
    Given User send GET Request for the LMS API endpoint with invalid AssignmentID
    When User create GET request with invalid AssignmentID from "<Sheetname>" and "<Testcase>"
    Then User "404" Not Found Status.

    Examples: 
      | Sheetname  | Testcase            |
      | Assignment | InvalidAssignmentID |

  @Assignment_Get_04
  Scenario Outline: Verify user able to retrieve a record with valid batchID
    Given User creates GET Request for the LMS API endpoint with valid BatchId
    When User create GET request with valid batch ID from "<Sheetname>" and "<Testcase>"
    Then User get status "200" OK

    Examples: 
      | Sheetname | Testcase |
      | Batch     | BatchID  |

  @Assignment_Get_05
  Scenario Outline: Verify user able to retrieve a record with invalid batchID
    Given User GET Request for the LMS API endpoint with invalid BatchId
    When User create GET request with invalid batchID from "<Sheetname>" and "<Testcase>"
    Then User create "404" Not Found Status with message and boolean success details

    Examples: 
      | Sheetname | Testcase     |
      | Batch     | InvalidBatch |
