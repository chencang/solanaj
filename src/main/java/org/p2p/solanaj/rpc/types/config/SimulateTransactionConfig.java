package org.p2p.solanaj.rpc.types.config;

import java.util.Map;

import lombok.Setter;
import org.p2p.solanaj.rpc.types.config.RpcSendTransactionConfig.Encoding;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
public class SimulateTransactionConfig {

    @JsonProperty("encoding")
    private Encoding encoding = Encoding.base64;

    @JsonProperty("accounts")
    private Map accounts = null;

    @JsonProperty("commitment")
    private String commitment = "finalized";

    @JsonProperty("sigVerify")
    private Boolean sigVerify = false;

    @JsonProperty("replaceRecentBlockhash")
    private Boolean replaceRecentBlockhash = false;
    public SimulateTransactionConfig(Map accounts) {
        this.accounts = accounts;
    }

    public SimulateTransactionConfig(Encoding encoding) {
        this.encoding = encoding;
    }


    public SimulateTransactionConfig(Encoding encoding,Boolean replaceRecentBlockhash) {
        this.replaceRecentBlockhash = replaceRecentBlockhash;
        this.encoding = encoding;
    }

    public static SimulateTransactionConfig defaultSimulateConfig() {
        // replaceRecentBlockhash: When true, RPC replaces the transaction's blockhash with the current valid one
        // This is necessary because:
        // - Solana blockhashes expire after ~60 seconds (~150 slots)
        // - Signed transactions from frontend may have expired blockhashes
        // - We want to simulate the transaction logic, not test blockhash validity
        // - With blockhash replacement, we can simulate old transactions without re-signing
        // sigVerify=false: Skip signature verification
        // Required because:
        // - If replaceRecentBlockhash=true, the blockhash changes, so the signature won't match
        // - Signature is based on original blockhash, but we're simulating with a new one
        // - For simulation, we care about execution logic, not signature validity
        return new SimulateTransactionConfig(Encoding.base58, true);

    }

}