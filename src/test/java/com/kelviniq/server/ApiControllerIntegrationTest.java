/*
package com.kelviniq.server;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import com.kelviniq.server.request.ClaimDeviceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kelviniq.server.controller.TelemetryController;
import com.kelviniq.server.request.TelemetryReading;
import com.kelviniq.server.request.TelemetryRequest;
import com.kelviniq.server.response.TelemetryResponse;
import com.kelviniq.server.service.DeviceClaimService;
import com.kelviniq.server.service.TelemetryIngestService;

class ApiControllerIntegrationTest {

    @Test
    void healthEndpointReturnsStatusOk() {
        ApiController controller = new ApiController();

        Map<String, String> response = controller.health();

        assertThat(response).containsEntry("status", "ok");
    }

    @Test
    void telemetryEndpointAcceptsPayload() {
        TelemetryController controller = new TelemetryController(new TelemetryIngestService());
        TelemetryRequest request = new TelemetryRequest(
                "a1b2c3d4e5f6",
                1754683200L,
                List.of(
                        new TelemetryReading(1754682300L, "ds18b20_0", 4.31),
                        new TelemetryReading(1754683200L, "pt100_0", 4.40)
                ),
                3812,
                -67,
                "0.4.2"
        );

        ResponseEntity<TelemetryResponse> response = controller.ingest(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().accepted()).isTrue();
        assertThat(response.getBody().deviceId()).isEqualTo("a1b2c3d4e5f6");
        assertThat(response.getBody().readingCount()).isEqualTo(2);
    }

    @Test
    void telemetryEndpointRejectsInvalidRequest() {
        TelemetryController controller = new TelemetryController(new TelemetryIngestService());
        TelemetryRequest request = new TelemetryRequest("", 0L, List.of(), 0, 0, "");

        ResponseEntity<TelemetryResponse> response = controller.ingest(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void deviceClaimEndpointAcceptsRequest() {
        var controller = new com.kelviniq.server.controller.DeviceController();
        var request = new ClaimDeviceRequest("a1b2c3d4e5f6", "ABCD1234");

        var response = controller.claimDevice(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().accepted()).isTrue();
    }
}
*/
