# 🚚 DriveAndDeliver - a Carrefour kata

> Here is a pretty free implementation exercise to be completed in a short period of time.\
> It aims to be a conversation starter during a future interview.\
> Since the goal is to discuss choices and good use of conceptes, we recommend that you spend no more than 2 hours on it, even if you haven't had the time to finish the mandatory User Stories. 

## Instructions
The exercise is made of a mandatory part, the [Minimum Valuable Product](#mvp), and [bonus features](#features-bonus) to make the most of your remaining time and stand out.\
The stories have no acceptance criteria, it's up to you to define them after your functional analysis of the story.

**If you are missing some information, make a choice and stick to it.**

### Constraints
- Spring-boot 3.x.x
- Java 21
- Git
- Enhance this `README.md` file -- _Explains the potential intricacies of your implementation and how to launch your project_.

### Delivery
The code should be available as a project in gitlab.

### Assessment
**There is no "right" way to do this exercise.**\
We are interested in your implementation choices, your technique, the code architecture and the compliance with the constraints.\
_Also pay attention to the size of your commits and their messages._

### Tips
To quickly create your project base, use [spring initializr](https://start.spring.io/)

## Exercise
### MVP
#### User Story
> As a customer, I can choose my delivery method.\
> The available delivery methods are: `DRIVE`, `DELIVERY`, `DELIVERY_TODAY`, `DELIVERY_ASAP`.

#### User Story
> As a customer, I can choose the day and time slot for my delivery.\
> The time slots are specific to the delivery method and can be booked by other customers.

### Bonus Features
The following features are optional and not exhaustive.\
They have no priority between them, you can implement the ones you are interested in or propose others.

#### REST API
- Propose an HTTP REST API to interact with the services implemented in the MVP
- Implement HATEOAS principles in your REST API
- Document the REST API
- Secure the API
- Use a non-blocking solution

#### Persistence
- Propose a data persistence solution
- Propose a cache solution

#### Stream
- Propose a data streaming solution
- Propose a solution for consuming and/or producing events

### CI/CD
- Propose a CI/CD system for the project
- Propose End-to-End tests for your application

### Packaging
- Create a container of your application
- Deploy your application in a pod
- Create a native image of your application