@Autowired
UserClient userClient;

//Get All Users
userClient.getAll().subscribe(
    data -> log.info("User: {}", data)
);

//Get User By Id
userClient.getById(1L).subscribe(
    data -> log.info("User: {}", data)
);

//Create a New User
userClient.save(new User(null, "Lokesh", "lokesh", "admin@email.com"))
    .subscribe(
        data -> log.info("User: {}", data)
    );


//Delete User By Id
userClient.delete(1L).subscribe(
    data -> log.info("User: {}", data)
);
