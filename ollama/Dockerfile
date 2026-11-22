FROM ollama/ollama:0.40.16

COPY Modelfile /tmp/Modelfile

ENV OLLAMA_MODELS=/models
ENV OLLAMA_KEEP_ALIVE=1h

RUN ollama serve & \
    pid=$! && \
    sleep 5 && \
    ollama pull gemma4:e2b && \
    ollama pull lfm3-audio:2b && \
    ollama create lfm3-audio-pt -f /tmp/Modelfile && \
    kill $pid && \
    rm -rf /tmp/*

ENTRYPOINT ["/bin/ollama"]

CMD ["serve"]