package com.twilio.sdk.fetchers;

import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resources.Account;

public class AccountFetcher extends Fetcher<Account> {

    private final String sid;

    public AccountFetcher(final String sid) {
        this.sid = sid;
    }

    @Override
    public Account execute(final TwilioRestClient client) {
        Request request = new Request(HttpMethod.GET, "/Accounts/" + sid + ".json");
        Response response = client.request(request);

        if (response.getStatusCode() != TwilioRestClient.HTTP_STATUS_CODE_OK) {
            throw new ApiException("Unable to find Account for Sid " + sid);
        }

        return Account.fromJson(response.getStream(), client.getObjectMapper());
    }
}