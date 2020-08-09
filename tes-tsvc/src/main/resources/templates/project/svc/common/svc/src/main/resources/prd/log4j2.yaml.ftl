Configuration:
  status: info

  Properties:
    Property:
      - name: log_base_dir
        value: /home/logs/tomcat
      - name: appName
        value: ${poolCode}

  Appenders:
    Sentry:
      name: Sentry
    Console:
      name: Console
      target: SYSTEM_OUT
      PatternLayout:
        Pattern: '[#|%d{yyyy-MM-dd''T''HH:mm:ss.SSS}|%p|%c{3}|%t|%X{userId}|%X{userType}|%X{guid}|%m\t%throwable%n|#]%n'

    #业务日志,以前的flume
    Syslog:
      - name: appSql
        appName: ${"$"}{appName}
        facility: LOCAL7
        protocol: UDP
        port: 521
        host: rsyslog.idc.cedu.cn
        newLine: true
        format: RFC5424
        includeMDC: true
        connectTimeoutMillis: 2000
        reconnectionDelayMillis: 5000
        enterpriseNumber: 18060
        mdcId: mdc
      - name: appBiz
        appName: ${"$"}{appName}
        facility: LOCAL7
        protocol: TCP
        port: 518
        host: rsyslog.idc.cedu.cn
        newLine: true
        format: RFC5424
        includeMDC: true
        connectTimeoutMillis: 2000
        reconnectionDelayMillis: 5000
        enterpriseNumber: 18060
        mdcId: mdc
        LoggerFields:
          KeyValuePair:
            - key: 'message'
              value: '%m'
            - key: 'priority'
              value: '%p'
            - key: 'category'
              value: '%c{3}'
            - key: 'priority'
              value: '%p'
            - key: 'exception'
              value: '%throwable'
            - key: 'timestamp'
              value: '%d{yyyy-MM-dd''T''HH:mm:ss.SSS}'
    #stdout
      - name: appStd
        appName: ${"$"}{appName}
        facility: LOCAL7
        protocol: UDP
        port: 517
        host: rsyslog.idc.cedu.cn
        newLine: true
        format: RFC5424
        includeMDC: true
        connectTimeoutMillis: 2000
        reconnectionDelayMillis: 5000
        enterpriseNumber: 18060
        mdcId: mdc
        LoggerFields:
          KeyValuePair:
            - key: 'message'
              value: '%m'
            - key: 'priority'
              value: '%p'
            - key: 'category'
              value: '%c{3}'
            - key: 'priority'
              value: '%p'
            - key: 'exception'
              value: '%throwable'
            - key: 'timestamp'
              value: '%d{yyyy-MM-dd''T''HH:mm:ss.SSS}'
      - name: appMetric
        appName: ${"$"}{appName}
        facility: LOCAL7
        protocol: TCP
        port: 519
        host: rsyslog.idc.cedu.cn
        newLine: true
        format: RFC5424
        includeMDC: true
        connectTimeoutMillis: 2000
        reconnectionDelayMillis: 5000
        enterpriseNumber: 18060
        mdcId: mdc
  Loggers:
    Root:
      level: info
      AppenderRef:
        - ref: Console
        - ref: appStd
        - ref: Sentry
          level: error
    Logger:
      - name: com.qingqing
        additivity: false
        level: info
        AppenderRef:
          - ref: Console
          - ref: appStd
          - ref: Sentry
            level: error
      - name: flume
        additivity: false
        level: info
        AppenderRef:
          - ref: appBiz
          - ref: Console
          - ref: appStd
      - name: metric
        additivity: false
        level: info
        AppenderRef:
          - ref: appMetric
      - name: druid.sql
        additivity: false
        level: debug
        AppenderRef:
          - ref: Console
          - ref: appSql
          - ref: Sentry
            level: error
