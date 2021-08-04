package www.bizpro.com.tw.app.mvvm.net;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import kotlin.text.Charsets;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchServiceTest {


    private SearchService service;
    private MockWebServer mockWebServer;

    @Before
    void init(){
        mockWebServer = new MockWebServer();
        service = new Retrofit.Builder().baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchService.class);
    }
    @After
    void dropdown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void searchRepo() throws InterruptedException {
        enqueueResponse("search-repo.json");
        RecordedRequest request = mockWebServer.takeRequest();
        service.searchRepo("coil+org:coil-kt");
    }

    private void enqueueResponse(String fileName) {
        HashMap map = new HashMap();
        InputStream inputStream = getClass().getResourceAsStream("api-response/$fileName");
        BufferedSource bufferedSource = Okio.buffer(Okio.source(inputStream));

        MockResponse response = new MockResponse();
        response.addHeader("0",fileName);
        try {
            mockWebServer.enqueue(response.setBody(bufferedSource.readString(Charsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
