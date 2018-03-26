# PrometheusLionAdapter
An adapter for Prometheus to receive test data from wild lion soap services.

## How to start

```
docker-compose up
```

## Exposed websites
### Prometheus
localhost:9115
### Grafana
localhost:3000
### LionAdapter
localhost:8080/register

localhost:8080/stateless

## Monitoring a service (registration based)

To monitor a service you have to register that service to the adapter. 
You can use the http post endpoint to do this. (/register)

The body needs to contain following JSON Body:

```
{
  "name": {{name}},
  "namespace": {{namespace}},
  "requestName": {{requestName}},
  "url": {{url}}
}
```

Name is only a label used to identify the service.
Namespace is required to build the SOAP request. 
RequestName is the name of the SOAP method used to check the endpoint.
Url is the endpoint of where to find the service.

## Monitoring a service (stateless)

Another way to monitor a service is by adding a job in the prometheus configuration.
The application delivers two metrics (test_successful and test_total) to be monitored in the prometheus format at /stateless.

The configuration required by prometheus looks like this:

```
job_name: 'VertragsAufbereitungZurSicherstellungIPSPrüfungNachVorgangssteuerungBES'
    scrape_interval: 5s
    metrics_path: /stateless
    params:
      target: ['http://lionadapter:8080/stateless']
      url: [{{url}}]
      requestName: [{{requestName}}]
      namespace: [{{namespace}}]
      name: [{{name}}]
    scheme: http
    static_configs:
      - targets: ['lionadapter:8080']
```

The parameters required are the same as in the example with the registration.