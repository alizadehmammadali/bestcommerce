package az.ibar.bcmsuser.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class clazz, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", clazz, fieldName, fieldValue));
    }

}
