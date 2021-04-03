package fr.uge.lootin.back.exception;

public final class Exceptions {

    public static final BadRequestException INVALID_EMAIL_ADDRESS =
            new BadRequestException("Invalid email address", 400001);

    public static final BadRequestException INVALID_MATCH =
            new BadRequestException("Invalid match, you can't match yourself", 400002);



    public static final ForbiddenException USERNAME_TAKEN = new ForbiddenException("Username is taken", 403001);
    public static final ForbiddenException INVALID_CREDENTIALS = new ForbiddenException("Incorrect username or password", 403002);

    public static NotFoundException gameNotFound(long id) {
        return new NotFoundException("Game with ID " + id + " not found.", 404001);
    }

    public static NotFoundException gameNotFound(String name) {
        return new NotFoundException("Game with ID " + name + " not found.", 404001);
    }


    public static NotFoundException matchNotFound(long id) {
        return new NotFoundException("Match with ID " + id + " not found.", 404002);
    }

    public static NotFoundException messageNotFound(long id) {
        return new NotFoundException("Message with ID " + id + " not found.", 404003);
    }

    public static NotFoundException imageNotFound(long id) {
        return new NotFoundException("Image " + id + " doesn't exist", 404004);
    }

    public static NotFoundException userNotFound(String username) {
        return new NotFoundException("user " + username + " doesn't exist", 404005);
    }

    public static NotFoundException userNotFound(Long id) {
        return new NotFoundException("user " + id + " doesn't exist", 404005);
    }

    public static NotFoundException attractionNotFound(String attraction) {
        return new NotFoundException("Attraction " + attraction + " doesn't exist", 404006);
    }
    
    public static BadRequestException usernameAlreadyExist(String username) {
        return new BadRequestException("Username " + username + " already exist", 404007);
    }
    
    public static BadRequestException emailAlreadyExist(String email) {
        return new BadRequestException("Email " + email + " already used", 404008);
    }


    private Exceptions() {
        throw new AssertionError();
    }
}
