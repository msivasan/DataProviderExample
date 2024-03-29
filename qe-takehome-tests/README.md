

*	Java 8
*	Maven 3
*	Git

If you need to adapt it to work with your environment, that is ok, just add comments in the files where appropriate.

The candidate will be writing Selenium tests against https://www.wikipedia.org/

## Verify Featured Languages
The candidate will write tests to verify the **Featured Languages**, such as English, Español, etc.  These can be seen in this image.

![Wikipedia Image](images/wikipedia-languages.png)

If the languages seen are different, that is ok.  Use the languages you see.
It is not required that all tests pass, maybe you will find a bug somewhere.  If a test isn't passing, add a comment explaining why.

## Requirements

1. Use TestNg `@DataProvider(s)`
1. Write a test method that verifies the languages are present by asserting their text value, based on a known list of languages.
    1. There should be a test result per language.
1. Write a test method that verifies the hyperlinks for the Featured Languages work, that is, they return a HTTP 200 status.
    1. There should be a test result per language.
1. Add methods or new classes where you think it is appropriate.
1. The final tests must execute when `mvn test` is run.

## Submission

To submit your work

1.  Execute `mvn package` which will generate the file `target/test-results.tar.gz`
1.  Submit the `target/qe-selenium-test-results.tar.gz` file to Greenhouse or contact your recruiter for help.






