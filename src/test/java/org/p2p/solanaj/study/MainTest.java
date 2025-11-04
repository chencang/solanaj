package org.p2p.solanaj.study;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.p2p.solanaj.core.Account;
import org.p2p.solanaj.core.AccountBasedTest;
import org.p2p.solanaj.core.AccountMeta;
import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.core.Transaction;
import org.p2p.solanaj.core.TransactionInstruction;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.AccountInfo;
import org.p2p.solanaj.rpc.types.ConfirmedTransaction;
import org.p2p.solanaj.rpc.types.ConfirmedTransaction.Instruction;
import org.p2p.solanaj.rpc.types.SimulatedTransaction;
import org.p2p.solanaj.rpc.types.config.SimulateTransactionConfig;
import org.p2p.solanaj.token.TokenManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * @author cang.chen
 * @date 2025/11/3 22:18
 */
public class MainTest  extends AccountBasedTest {

    private final RpcClient client = new RpcClient(Cluster.MAINNET);
    public final TokenManager tokenManager = new TokenManager(client);

    private static final PublicKey USDC_TOKEN_MINT = new PublicKey("EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v");
    private static final long LAMPORTS_PER_SOL = 1000000000L;


    @BeforeEach
    public void beforeMethod() throws InterruptedException {
        // Prevent RPCPool rate limit
        Thread.sleep(200L);
    }

    @Test
    public void simulateTransactionWithCallDataTest() throws RpcException {
        String callData = "从前端获取的Base58字符串";
        callData
            =
            "GZrXdVidzR1JfW24ZdQu8MUDy5wprmsNZx56FvyqBncomtxYGcmgBdtK7uXUG8yv8Lc1BdJ4R63pPdiftnZ5SvgPprmUvmUttjsZVQnGm4X7A3DxNyFWunD7bCyBtfHFetUzvAL9dkiavjcEbA3TfzVx6z4iczoWsi2ZY32Ed7zYJmu15pg8iHWpFBnqTydcjVbfgvWv6uLNwRgps8G4qTs3Nhom1LyZDra3PUYTnhKds66Q7Pn2b4r5YTiq6HaWnjV6E2UsyjnmbhHY5rNzNeLEcvPBEF9HuiGjkrxKtkUP5kqhJQUSKcuq3s5uYZhb49Afiy1tRGcRoXjUvsBeBt17Z9PyZjWpKcbfyaHcw47RVbdwukBTPcYeo3MtEc19y228m1LsEkhv2LrfM8DLC2oqhHbbkpoFZHDnWGA7dTH8iD9DAwYMxYo5yTQ5LwbijMWnqT1dFWVSzvRDPzMsHD33PfDnNe6zTHxDZ9tMemuAzdPscd54jDzMirzHyrzrghrE6EjFyh3LMaiLiyd4fw3Q7fc7VYvNw6JYbzvnvTQNcCfZ5k29rWZoissnL9WADdhMPfCbor68QmexGchQCFLKK6ksp2GC9Ft7qGKq99g8JiWueNfBSFYT5m8FM6uuoW5xoq7QV9xyeWNhwC6bFBnh83tcEQ1LTYnx6DG2wzq49Gvss1wp1bz4rdw7rm9qv5PReibsYGnQcxQ6e6sLqadekNfs46bKRN8AuJUvcTkbcVqLjUuaDSK92cye6xsZJ9YFRSFoNePpRBjSwqXTaAixrSEhfAU21TN6YFpHQtktx2TagkZUKpFM6ZithM8EGDwpbkyCaS96XYj31YCGzhjgiyAhihHBQggijm875gqF7pvEvSTcvF2wqg9dcySpkx6rdgpqh87W7QhTo1fjR68yTqYoRYoWTgYoXm6QQBPyv1oq9zfXeQSNozZhzpx9enQvmLEaqf8eYRTzJy9g1RC78s4cgGUVodwy4yWXqSk14rmHhCXRursBuB1dhhZiFNqmkexhwGxY3LY14bxpxWqoJPrUr73g6Ldh2WgWy6WQ5sRF7yGxXELkM1forTkye";
        SimulatedTransaction result = client.getApi().simulateTransaction(callData, SimulateTransactionConfig.defaultSimulateConfig());
        LOGGER.info("✅simulate success！Logs size: " + result.getValue().getLogs().size());
        StringBuilder logsBuilder = new StringBuilder("Logs content:\r\n");
        for (String log : result.getValue().getLogs()) {
            logsBuilder.append(log).append("\r\n");
        }
        LOGGER.info(logsBuilder.toString());
        if (result.getValue().getAccounts() != null) {
            LOGGER.info("Accounts count: " + result.getValue().getAccounts().size());
        }
    }

}
