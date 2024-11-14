package use_case.freePlayMode;

/**
 * The Free Play Mode use case
 */
public interface FreePlayInputBoundary {

    void hit(FreePlayInputData freePlayInputData);

    void stand(FreePlayInputData freePlayInputData);
}
