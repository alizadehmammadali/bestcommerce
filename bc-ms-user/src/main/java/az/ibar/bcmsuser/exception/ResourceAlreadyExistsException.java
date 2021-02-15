package az.ibar.bcmsuser.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(Class clazz,String fieldName,String fieldValue) {
        super(String.format("%s is already exists with %s : '%s' .Please try another one", clazz, fieldName, fieldValue));
    }
}
