package filter;


import akka.stream.Materializer;
import play.mvc.Filter;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Function;

/**
 * Created by zg on 2016/6/21.
 */
public class ExampleFilter extends Filter {

    private final Executor exec;

    /**
     * @param mat  This object is needed to handle streaming of requests
     *             and responses.
     * @param exec This class is needed to execute code asynchronously.
     *             It is used below by the <code>thenAsyncApply</code> method.
     */
    @Inject
    public ExampleFilter(Materializer mat, Executor exec) {
        super(mat);
        this.exec = exec;
    }

    @Override
    public CompletionStage<Result> apply(
            Function<RequestHeader, CompletionStage<Result>> next,
            RequestHeader requestHeader) {
        if (requestHeader.method().toUpperCase().equals("GET")) {
            System.out.println(requestHeader.uri());
        }
        System.out.println(requestHeader.getQueryString("tsp"));
        return next.apply(requestHeader).thenApplyAsync(
                result -> result.withHeader("X-ExampleFilter", "foo"),
                exec
        );
    }
}
