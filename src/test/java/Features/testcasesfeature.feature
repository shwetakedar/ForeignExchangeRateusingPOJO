Feature: Rate Foreign Exchange API

@ValidResponse @LatestRate
Scenario Outline: To verify that the valid response is received

Given Rates API for latest Foreign Exhchange rates
When user calls latest Foreign Exchange Rates API <resource> with GET http request
And the API call is displayed with status code <statusCode>
And the response body is written to a json file
And json file is read
Then the response body should contain base value as <currency>
And response body should contain date as <date>
And response body should contain rates

Examples:
| statusCode | resource   | currency |     date     |
|   200      | 'latest'   | 'EUR'    | '2021-01-15' |
|   200      |'2010-01-12'| 'EUR'    | '2010-01-12' |