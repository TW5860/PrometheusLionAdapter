FROM centos:7

USER root

ARG PROMETHEUS_VERSION=2.1.0

RUN set -ex; \ 
    curl -o /tmp/prometheus.tar.gz -L "https://github.com/prometheus/prometheus/releases/download/v2.1.0/prometheus-${PROMETHEUS_VERSION}.linux-amd64.tar.gz"; \
    tar xfz /tmp/prometheus.tar.gz -C /tmp; \
    rm /tmp/prometheus.tar.gz; \
    mkdir -p /etc/prometheus /usr/share/prometheus; \
    mv /tmp/prometheus-${PROMETHEUS_VERSION}.linux-amd64/prometheus /bin/prometheus; \
    mv /tmp/prometheus-${PROMETHEUS_VERSION}.linux-amd64/promtool /bin/promtool; \
    mv /tmp/prometheus-${PROMETHEUS_VERSION}.linux-amd64/prometheus.yml /etc/prometheus/prometheus.yml; \
    mv /tmp/prometheus-${PROMETHEUS_VERSION}.linux-amd64/console_libraries/ /usr/share/prometheus/console_libraries/; \
    mv /tmp/prometheus-${PROMETHEUS_VERSION}.linux-amd64/consoles/ /usr/share/prometheus/consoles/; \
    rm -r /tmp/prometheus-${PROMETHEUS_VERSION}.linux-amd64; \
    ln -s /usr/share/prometheus/console_libraries /usr/share/prometheus/consoles/ /etc/prometheus/; \
    mkdir -p /prometheus;

RUN chown -R nobody: /etc/prometheus /prometheus

USER nobody
EXPOSE 9090
VOLUME [ "/prometheus" ]
WORKDIR /prometheus
CMD [ "/bin/prometheus", \
      "--config.file=/etc/prometheus/prometheus.yml", \
      "--storage.tsdb.path=/prometheus", \
      "--web.console.libraries=/usr/share/prometheus/console_libraries", \
      "--web.console.templates=/usr/share/prometheus/consoles" ]
