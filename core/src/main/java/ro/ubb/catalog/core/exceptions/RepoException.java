package ro.ubb.catalog.core.exceptions;

public class RepoException extends RuntimeException{
    public RepoException(String error){
        super(error);
    }
}
