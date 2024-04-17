package eu.elex8x.apicore.validator;

/**
 * Interface which contains methods to verify the DTO and Entity params
 * @param <E> Entity class
 * @param <D> Data transfer class
 */
public interface Validator<E, D> {
    boolean validateEntity(E entity);
    boolean validateDataTransferObject(D object);
}
