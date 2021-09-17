# adevinta App
This is the project for Adevinta challenge

Architecture:
- MVVM/Clean - Since this structure allow us to have the responsabilities split and defined in a clearer way. Following clean principles.

Dependencies:
- Retrofit: One of the best ways of consuming REST APIs. Allow us to define the base request with interceptors.
- Koin: Used for dependency injection of the projecty. Help us to define easily the components such us repositories, use cases, sources and viewmodels, and their life cycles when app is runnign. Easy to use it with viewmodels.
- MockK: Used for the unit testing process, I see advantages of using this like avoiding final classes issues when calling some methods in the tests. Verifying process is easy to implement and more readable.
- Glide: Used for handling images. It is quite better in terms of performance compared to other libraries like Picasso. Easy to implement resizing and rounded corners transformations that the challenge required.

