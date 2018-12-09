package com.cat.eosinfo

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.repo.model.ServerInfo
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DataInstrumentTest{

    @Test
    fun testParseServerInfo() {
        val rawJsonServerInfo = "{\n" +
                "    \"server_version\": \"60c8bace\",\n" +
                "    \"chain_id\": \"aca376f206b8fc25a6ed44dbdc66547c36c6c33e3a119ffbeaef943642f0e906\",\n" +
                "    \"head_block_num\": 31041593,\n" +
                "    \"last_irreversible_block_num\": 31041261,\n" +
                "    \"last_irreversible_block_id\": \"01d9a6ed74ad1d3a4d04d665b232158042ec528a505c53476a1a64f190250e8e\",\n" +
                "    \"head_block_id\": \"01d9a8396abc62f28a9a8ecae190c0353ad3e27bb10be76bd3ab302e5a76f82c\",\n" +
                "    \"head_block_time\": \"2018-12-08T15:52:52.000\",\n" +
                "    \"head_block_producer\": \"eosriobrazil\",\n" +
                "    \"virtual_block_cpu_limit\": 1933212,\n" +
                "    \"virtual_block_net_limit\": 1048576000,\n" +
                "    \"block_cpu_limit\": 150508,\n" +
                "    \"block_net_limit\": 1042880,\n" +
                "    \"server_version_string\": \"v1.4.2\"\n" +
                "}"


        val serverInfo = ServerInfo.fromJSON(rawJsonServerInfo)
        Assert.assertTrue(serverInfo.head_block_num == 31041593)
        Assert.assertTrue(serverInfo.head_block_id == "01d9a8396abc62f28a9a8ecae190c0353ad3e27bb10be76bd3ab302e5a76f82c")
    }

    fun testBlock() {
        val rawBlock = "{\n" +
                "    \"timestamp\": \"2018-12-08T15:52:52.000\",\n" +
                "    \"producer\": \"eosriobrazil\",\n" +
                "    \"confirmed\": 0,\n" +
                "    \"previous\": \"01d9a838e04177b1478ecf0a9133e4ad13981947080fc3de781e6ef12c0cf7cb\",\n" +
                "    \"transaction_mroot\": \"d4cc328d07a9b0c8ff222ff579f120d625f6e5b0f5100b4c1a5858c209cb0a8d\",\n" +
                "    \"action_mroot\": \"25b694e62893fcc2e329b8cc5b59e14949198c098a3ed3631366c1159b35dba8\",\n" +
                "    \"schedule_version\": 578,\n" +
                "    \"new_producers\": null,\n" +
                "    \"header_extensions\": [],\n" +
                "    \"producer_signature\": \"SIG_K1_KUz1DrqG6NtJ4XXSdZQv8JtuMG25UpZLcBg1pUkFXDMu3Djw7ZiGVmgUxSPK78z6Uhu7jyM566yHiSo1JJ8N4FTgYSAZZE,\"" +
                "    \"id\": \"01d9a8396abc62f28a9a8ecae190c0353ad3e27bb10be76bd3ab302e5a76f82c\",\n" +
                "    \"block_num\": 31041593,\n" +
                "    \"ref_block_prefix\": 3398343306," +
                "    \"transactions\": [\n" +
                "        {\n" +
                "            \"status\": \"executed\",\n" +
                "            \"cpu_usage_us\": 128,\n" +
                "            \"net_usage_words\": 0,\n" +
                "            \"trx\": \"4bb55b88f510d1149947cbe75397732fd469d48729374a4be5724a6dcf4ee257\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"status\": \"executed\",\n" +
                "            \"cpu_usage_us\": 100,\n" +
                "            \"net_usage_words\": 0,\n" +
                "            \"trx\": \"d586b77b4c413f2e67394876b1a893325eae3633aac50af822a1827b3d7bd749\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"status\": \"executed\",\n" +
                "            \"cpu_usage_us\": 121,\n" +
                "            \"net_usage_words\": 0,\n" +
                "            \"trx\": \"e8551ed1abfa9983b72531cce63f1c2eceb7b5fd31a3c1307dfa22c8fadc6a03\"\n" +
                "        }," +
                "{\n" +
                "            \"status\": \"executed\",\n" +
                "            \"cpu_usage_us\": 739,\n" +
                "            \"net_usage_words\": 32,\n" +
                "            \"trx\": {\n" +
                "                \"id\": \"7afcf9df54b29a2212385b7eee74de194d1fe079d58922bbacab9c61fb5ac6dc\",\n" +
                "                \"signatures\": [\n" +
                "                    \"SIG_K1_Kbt7keG9GSsLi6Rohyjf1uYyxF1zsDmqRWuMX2FmHoZD2im6rjXTSoA26gtfPfZvdkCdx1Bm7joGqMJdthurHVF4tD2SLC\"\n" +
                "                ],\n" +
                "                \"compression\": \"none\",\n" +
                "                \"packed_context_free_data\": \"\",\n" +
                "                \"context_free_data\": [],\n" +
                "                \"packed_trx\": \"e2f60b5ceda64d04d6650000000001a0904b982a0f918e00405647ed48b1ba0150c810412197305500000000a8ed323272c02302000000000000206de197240101c1de75db6073b84febb8dd9bb94bc75beab2674fe92af123590f7d0351d41f812588a1d03524e3c343051225e92347e82b1f5b627eccf59a7f1d7218b6d393dc17c7024dd972dbe51395e5b002a3c16bc5436f02a2011ce5e14be80300000000000000\",\n" +
                "                \"transaction\": {\n" +
                "                    \"expiration\": \"2018-12-08T16:52:50\",\n" +
                "                    \"ref_block_num\": 42733,\n" +
                "                    \"ref_block_prefix\": 1708524621,\n" +
                "                    \"max_net_usage_words\": 0,\n" +
                "                    \"max_cpu_usage_ms\": 0,\n" +
                "                    \"delay_sec\": 0,\n" +
                "                    \"context_free_actions\": [],\n" +
                "                    \"actions\": [\n" +
                "                        {\n" +
                "                            \"account\": \"luckyeosdice\",\n" +
                "                            \"name\": \"resolvebet\",\n" +
                "                            \"authorization\": [\n" +
                "                                {\n" +
                "                                    \"actor\": \"eosdice12345\",\n" +
                "                                    \"permission\": \"active\"\n" +
                "                                }\n" +
                "                            ],\n" +
                "                            \"data\": {\n" +
                "                                \"bet_id\": 140224,\n" +
                "                                \"sig\": \"SIG_K1_Kj86roPsJMCNUEYTSzys1G6xCeQ3SNFryy1PnizhBsXHKM5fYvov9vk4Kv7wN9ainS4c2QZQyL98t1RPGpo38eLXXYsRar\",\n" +
                "                                \"sig_hash\": \"7218b6d393dc17c7024dd972dbe51395e5b002a3c16bc5436f02a2011ce5e14b\",\n" +
                "                                \"price\": 1000\n" +
                "                            },\n" +
                "                            \"hex_data\": \"c02302000000000000206de197240101c1de75db6073b84febb8dd9bb94bc75beab2674fe92af123590f7d0351d41f812588a1d03524e3c343051225e92347e82b1f5b627eccf59a7f1d7218b6d393dc17c7024dd972dbe51395e5b002a3c16bc5436f02a2011ce5e14be803000000000000\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"transaction_extensions\": []\n" +
                "                }\n" +
                "            }\n" +
                "        }," +
                "}"

        val block = Block.fromJSON(rawBlock)
        Assert.assertTrue(block.id == "01d9a8396abc62f28a9a8ecae190c0353ad3e27bb10be76bd3ab302e5a76f82c")
        Assert.assertTrue(block.producer == "eosriobrazil")
        Assert.assertTrue(block.producer_signature == "SIG_K1_KUz1DrqG6NtJ4XXSdZQv8JtuMG25UpZLcBg1pUkFXDMu3Djw7ZiGVmgUxSPK78z6Uhu7jyM566yHiSo1JJ8N4FTgYSAZZE")
        Assert.assertTrue(block.transactions != null)
        Assert.assertTrue(block.transactions!!.size == 4)
        Assert.assertTrue(block.transactions!![3].trx != null)
        Assert.assertTrue(block.transactions!![3].trx!!.id == "7afcf9df54b29a2212385b7eee74de194d1fe079d58922bbacab9c61fb5ac6dc")
        Assert.assertTrue(block.transactions!![3].trx!!.transaction != null)
        Assert.assertTrue(block.transactions!![3].trx!!.transaction!!.expiration == "2018-12-08T16:52:50")
        Assert.assertTrue(block.transactions!![3].trx!!.transaction!!.actions.size == 1)
        Assert.assertTrue(block.transactions!![3].trx!!.transaction!!.actions[0].account == "luckyeosdice")
        Assert.assertTrue(block.transactions!![3].trx!!.transaction!!.actions[0].data != null)
    }
}