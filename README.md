# adevinta App
This is the project for Adevinta challenge

Architecture:
- MVVM/Clean - Since this structure allow us to have the responsabilities split and defined in a clearer way. Following clean principles.

Dependecies:
- Retrofit: one of the best ways of consuming REST APIs. Allow us define the base request with interceptors.
- Koin: Used for dependency injection of the projecty. Help us to define easily the components such us repositories, use cases, sources and viewmodels. Easy to use it with viewmodels.
- Mockk: Used for the unit testing process, I see advantages of using it like avoiding final classes issues when calling some methods in the tests. Verifying is easy to implement and more readable.
- Glide: Used for handling images. It is quite better in terms of performance compared to other libraries like Picasso. Easy to implement resizing and rounded corners transformations.

