## Review Questions

**a) Identify a couple of examples that use AssertJ expressive methods chaining.**

```assertThat( found ).isEqualTo(alex);```

```assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());```


**b) Identify an example in which you mock the behavior of the repository (and avoid involving a
database).**

In the test class "B_EmployeeService_UnitTest", the repository is mocked using the Mockito framework (@Mock), 
where the repository mock does not persist the data to the database. 
Instead, it simulates the behavior of the repository through the use of methods such as:

```Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);```

**c) What is the difference between standard @Mock and @MockBean?**

Simply put, the difference between using @Mock or @MockBean is the context of the test.
If we are testing a class outside the Spring context, we can use JUnit and Mockito to mock the objects (@Mock).
However, when inside the Spring Boot context (through the use of the annotations @SpringBootTest or @WebMvcTest), 
we must use the annotation @MockBean to mock the objects as a Spring Bean.


**d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be
used?**

The file "application-integrationtest.properties" contains the properties of an environment that will be used to run the integration tests.

To instruct Spring Boot to run the integration tests in the context described in the file, we must use the annotation:

```@TestPropertySource(locations = "application-integrationtest.properties")```

The file will be mostly used when we want to interact with external entities or services
such as a database or a web service when running integration tests.


**e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed
with SpringBoot. Which are the main/key differences?**

The main differences between the three test strategies are:
- The C strategy runs the test using a simplified and light environment
by using the annotation @WebMvcTest. When it comes to the D and E strategies they both
run the tests in a full web context through the use of the annotation @SpringBootTest.
- The main difference between the D and E strategies is the client which is being used
to simulate the web environment. The D strategy uses the Spring MVC test support (@MockMvc), 
whereas the E strategy uses the RestTemplate to create realistic HTTP requests.
- The C strategy comes closer to a unit test, whereas the D and E strategies are pure integration tests.

