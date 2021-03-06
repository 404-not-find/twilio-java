/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.marketplace.installedaddon;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class InstalledAddOnExtensionUpdater extends Updater<InstalledAddOnExtension> {
    private final String pathInstalledAddOnSid;
    private final String pathSid;
    private final Boolean enabled;

    /**
     * Construct a new InstalledAddOnExtensionUpdater.
     *
     * @param pathInstalledAddOnSid The SID of the InstalledAddOn resource with the
     *                              extension to update
     * @param pathSid The SID of the InstalledAddOn Extension resource to update
     * @param enabled Whether the Extension should be invoked
     */
    public InstalledAddOnExtensionUpdater(final String pathInstalledAddOnSid,
                                          final String pathSid,
                                          final Boolean enabled) {
        this.pathInstalledAddOnSid = pathInstalledAddOnSid;
        this.pathSid = pathSid;
        this.enabled = enabled;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated InstalledAddOnExtension
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public InstalledAddOnExtension update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.PREVIEW.toString(),
            "/marketplace/InstalledAddOns/" + this.pathInstalledAddOnSid + "/Extensions/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("InstalledAddOnExtension update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return InstalledAddOnExtension.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (enabled != null) {
            request.addPostParam("Enabled", enabled.toString());
        }
    }
}