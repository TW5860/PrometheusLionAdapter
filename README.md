# PrometheusLionAdapter
An adapter for Prometheus to receive test data from wild lion soap services.

## Monitoring a service

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
