package funcatron.intf;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * Install an operation in the current context.
 * <p>
 * What's an Operation? It's a way that the Func Runner communicates with the Func Bundle.
 * <p>
 * What kind of operation would one create or override?
 * <p>
 * The prototypical operation is "getSwagger". By default, the Swagger file is stored in
 * the funcatron.yml or funcatron.json, but we may want to supply the Swagger information by
 * looking through the Spring Boot annotations instead and get the Swagger info from Spring.
 */
public interface OperationProvider extends OrderedProvider {
    /**
     * Install an operation.
     *
     * @param addOperation a function to add a named operation. A named function takes a map, logger, and returns something.
     *                     This function takes the name of the operation and the operation.
     * @param getOperation get a named operation, or null if the operation doesn't exist.
     * @param addEndOfLife Add a function at the end of life. If there's anything allocated by the installation of the
     *                     operation (like creating a JDBC pool), then the operation should be released by the
     *                     end of life function.
     * @param classLoader  the ClassLoader for the Func Bundle
     * @param logger       the Logger
     */
    void installOperation(BiFunction<String, BiFunction<Map<Object, Object>, Logger, Object>, Void> addOperation,
                          Function<String, BiFunction<Map<Object, Object>, Logger, Object>> getOperation,
                          Function<Function<Logger, Void>, Void> addEndOfLife,
                          ClassLoader classLoader,
                          Logger logger);


}
