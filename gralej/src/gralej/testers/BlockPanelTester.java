package gralej.testers;

import gralej.blocks.Block;
import gralej.controller.StreamInfo;
import gralej.blocks.BlockPanel;
import gralej.om.Entities;
import gralej.parsers.GraleParserFactory;
import gralej.parsers.IDataPackage;
import gralej.parsers.IGraleParser;
import gralej.parsers.IParseResultReceiver;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlockPanelTester {

    final static String TEST_MESSAGE = "!newdata\"[peter,likes,mary]\"(S0(1\"phrase\")(V2\"phon\"(L3(#4 17)(Z5(#6 18))))(V7\"synsem\"(S8(9\"synsem\")(V10\"loc\"(S11(12\"loc\")(V13\"cat\"(S14(15\"cat\")(V16\"head\"(#17 10))(V18\"val\"(S19(20\"val\")(V21\"subj\"(L22))(V23\"comps\"(L24))))))(V25\"cont\"(#26 13))))(V+27\"nonloc\"(S+28(+29\"mgsat nonloc\")))))(V+30\"daughters\"(S31(32\"hs_struc\")(V33\"hdtr\"(#34 16))(V35\"ndtr\"(#36 15))))(V+37\"dtrs\"(L38(#39 15)(#41 16))))(R42 17(A43\"peter\"))(R44 13(S45(46\"present_rel\")(V47\"soa_arg\"(S48(49\"like_rel\")(V50\"liker\"(#51 3))(V52\"liked\"(#53 2))))))(R54 18(L55(#56 8)(Z57(#58 9))))(R59 8(A60\"likes\"))(R61 9(L62(A63\"mary\")))(R64 2(S65(66\"ref\")(V67\"gen\"(S68(69\"fem\")))(V70\"num\"(S71(72\"sg\")))(V73\"pers\"(S74(75\"third\")))))(R76 3(S77(78\"ref\")(V79\"gen\"(S80(81\"masc\")))(V82\"num\"(S83(84\"sg\")))(V85\"pers\"(S86(87\"third\")))))(R88 16(S89(90\"phrase\")(V91\"phon\"(#92 18))(V93\"synsem\"(S94(95\"synsem\")(V96\"loc\"(S97(98\"loc\")(V99\"cat\"(S100(101\"cat\")(V102\"head\"(#103 10))(V104\"val\"(S105(106\"val\")(V107\"subj\"(#108 12))(V109\"comps\"(#110 11))))))(V111\"cont\"(#112 13))))(V+113\"nonloc\"(S+114(+115\"mgsat nonloc\")))))(V+116\"daughters\"(S117(118\"hc_struc\")(V119\"hdtr\"(#120 6))(V121\"ndtr\"(#122 7))))(V+123\"dtrs\"(L124(#125 6)(#127 7)))))(R128 11(L129))(R130 12(L131(#132 1)))(R133 1(S134(135\"synsem\")(V136\"loc\"(S137(138\"loc\")(V139\"cat\"(S140(141\"cat\")(V142\"head\"(S143(144\"noun\")(V145\"case\"(S146(147\"nom\")))(V+148\"mod\"(S+149(+150\"none\")))(V151\"pred\"(S152(153\"minus\")))))(V154\"val\"(S155(156\"val\")(V157\"subj\"(L158))(V159\"comps\"(#160 14))))))(V161\"cont\"(S162(163\"nom_obj\")(V164\"index\"(#165 3))))))(V+166\"nonloc\"(S+167(+168\"mgsat nonloc\")))))(R169 14(L170))(R171 15(S172(173\"word\")(V174\"phon\"(L175(#176 17)))(V177\"synsem\"(#178 1))(V179\"arg_st\"(#180 14))))(R181 6(S182(183\"word\")(V184\"phon\"(L185(#186 8)))(V187\"synsem\"(S188(189\"synsem\")(V190\"loc\"(S191(192\"loc\")(V193\"cat\"(S194(195\"cat\")(V196\"head\"(#197 10))(V198\"val\"(S199(200\"val\")(V201\"subj\"(#202 12))(V203\"comps\"(#204 0))))))(V205\"cont\"(#206 13))))(V+207\"nonloc\"(S+208(+209\"mgsat nonloc\")))))(V210\"arg_st\"(L211(#212 1)(Z213(#214 0))))))(R215 0(L216(#217 5)(Z218(#219 11))))(R220 5(S221(222\"synsem\")(V223\"loc\"(S224(225\"loc\")(V226\"cat\"(S227(228\"cat\")(V229\"head\"(S230(231\"noun\")(V232\"case\"(S233(234\"acc\")))(V+235\"mod\"(S+236(+237\"none\")))(V238\"pred\"(S239(240\"minus\")))))(V241\"val\"(S242(243\"val\")(V244\"subj\"(L245))(V246\"comps\"(#247 4))))))(V248\"cont\"(S249(250\"nom_obj\")(V251\"index\"(#252 2))))))(V+253\"nonloc\"(S+254(+255\"mgsat nonloc\")))))(R256 4(L257))(R258 7(S259(260\"word\")(V261\"phon\"(#262 9))(V263\"synsem\"(#264 5))(V265\"arg_st\"(#266 4))))(R267 10(S268(269\"verb\")(V270\"vform\"(S271(272\"fin\")))(V273\"aux\"(S274(275\"minus\")))(V+276\"inv\"(S+277(+278\"boolean\")))(V+279\"marking\"(S+280(+281\"unmarked\")))(V+282\"mod\"(S+283(+284\"none\")))(V285\"pred\"(S286(287\"plus\")))))(T288\"phrase:[peter,likes,mary]\"0(T289\"word:[peter]\"172)(T290\"phrase:[likes,mary]\"89(T291\"word:[likes]\"182)(T292\"word:[mary]\"259)))\n";
    final static String STREAM_INFO_TYPE = "grisu";
    public JFrame lastFrame;
    public BlockPanel lastPanel;
    public IDataPackage lastDataPackage;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        new BlockPanelTester(args);
    }

    public BlockPanelTester() throws Exception {
        this(new String[0]);
    }

    public BlockPanelTester(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err
                    .println("-- failed to set the system's native look and feel");
        }
        StreamInfo streamInfo = new StreamInfo(STREAM_INFO_TYPE);
        IGraleParser parser = GraleParserFactory.createParser(streamInfo);

        InputStream in = null;

        if (args.length == 0)
            in = stringToInputStream(TEST_MESSAGE);
        else if (args[0].equals("-"))
            in = System.in;
        else if (!args[0].startsWith("-"))
            in = new FileInputStream(args[0]);
        else {
            String opt = args[0].substring(1);
            if (opt.equals("server")) {
                int port = 1080;
                if (args.length > 1)
                    port = Integer.parseInt(args[1]);
                System.err.println("-- Waiting for a client on port " + port);
                ServerSocket ss = new ServerSocket(port);
                Socket s = ss.accept();
                System.err.println("-- Connected: " + s);
                ss.close();
                in = s.getInputStream();
            } else if (opt.equals("-h") || opt.equals("-?")) {
                System.err
                        .println("java -jar gralej.jar [filename | -server [port]]");
                System.exit(1);
            } else
                throw new Exception("unknown option: " + opt);
        }

        parser.parse(in, streamInfo, new IParseResultReceiver() {
            public void newDataPackage(final IDataPackage result) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        show(result);
                    }
                });
            }

            public void streamClosed(InputStream is, StreamInfo meta, Exception exception) {
                System.err.println("-- stream '" + meta.getType() + "' closed");
                if (exception != null)
                    System.err.println("-- exception: " + exception);
            }

        });

        if (in != System.in && System.in != null) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
                        String line;
                        while ((line = r.readLine()) != null) {
                            if (line.equals("quit"))
                                break;
                            try {
                                final Block b = lastPanel.getContent().getDescendant(line);
                                if (b != null) {
                                    SwingUtilities.invokeLater(new Runnable() {
                                        public void run() {
                                            lastPanel.ensureVisible(b);
                                            lastPanel.centerBlock(b);
                                            lastPanel.setSelectedBlock(b);
                                        }
                                    });
                                    if (b.getModel() != null) {
                                        System.out.println(Entities.toTraleDesc(b.getModel()));
                                    }
                                }
                            }
                            catch (Exception ex) {

                            }
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }

    void show(IDataPackage datapak) {
        JFrame f = new JFrame();
        f.setTitle(datapak.getTitle());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        final BlockPanel blockPanel = new BlockPanel(datapak.getModel());
        blockPanel.addSelectionListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Block b = blockPanel.getSelectedBlock();
                String path = null;
                if (b != null)
                    path = b.getPath();
                System.err.println("selection changed; path: " + path);
                if (b != null && b.getModel() != null) {
                    try {
                        String desc = Entities.toTraleDesc(b.getModel());
                        System.err.println(desc);
                    }
                    catch (UnsupportedOperationException ex) {

                    }
                }
            }
        });
        JPanel view = blockPanel.getUI();
        //view.setOpaque(true);
        //f.setContentPane(view);
        f.add(view);
        f.pack();
        f.setVisible(true);
        lastFrame = f;
        lastPanel = blockPanel;
        lastDataPackage = datapak;
    }

    static InputStream stringToInputStream(String s) {
        byte[] bs = new byte[s.length()];
        for (int i = 0; i < s.length(); ++i)
            bs[i] = (byte) s.charAt(i);
        return new ByteArrayInputStream(bs);
    }
}
