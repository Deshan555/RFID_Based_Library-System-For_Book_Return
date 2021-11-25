/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import static Interfaces.mail.template;
import Other_Services.email;
import Other_Services.email_thread;
import Other_Services.time_management;
import SQL_Connector.Connector;
import SQL_Connector.Validation_Bookowner;
import SQL_Connector.Validation_Tax;
import SQL_Connector.date;
import static SQL_Connector.get_Tax.get_sum;
import SQL_Connector.load_bookname;
import SQL_Connector.take_count;
import Sql_Lite.Load_Settings;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.plaf.basic.BasicInternalFrameUI;


/**
 *
 * @author Jayashanka Deshan
 */
public class menu_3 extends javax.swing.JInternalFrame {

    int card_id = 0;
    
    int status = 0;
    
    int tax_data = 0;
    
    String mail = null;
    
    String user_name = null;
    
    ArrayList<String> book = new ArrayList<String>();
    
    public menu_3(int ID)
    {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        BasicInternalFrameUI bis = (BasicInternalFrameUI) this.getUI();
        
        bis.setNorthPane(null);
        
        this.card_id = ID;
        
        System.out.println(card_id);
        
        jLabel13.setVisible(false);
        
        jLabel12.setVisible(false);
        
        jLabel25.setVisible(false);
               
        reload_all();
        
        get_mailAddress();
    }
    
    public void reload_all()
    {
      first_status();
      
      try
      {
          card_status();
           
          load_lending_table();
          
          load_time();
          
          int sum = get_sum(card_id);
        
          jTextField6.setText("LKR "+String.valueOf(sum)+".00");
      }
      catch(Exception ERROR)
      {
          System.out.print("Error : "+ERROR);
      }
 
    }
    
    public void first_status()
    {
       slot_1.setText("");
       
       slot_2.setText("");
       
       slot_3.setText("");
       
       jTextField15.setText("");
       
       jTextField11.setText("");
       
       jTextField3.setText("");
       
       jTextField12.setText("");
       
       jTextField9.setText("");
       
       rdate.setText("");
       
       rrdate.setText("");
       
       jTextField6.setText("");
       
       tax.setText("");
       
       book_id.setText("");
       
        jLabel13.setVisible(false);
        
        jLabel12.setVisible(false);
        
        jLabel25.setVisible(false);
        
        cbox.setSelected(false);
    }
    
    public void card_status()
    {
            String count =  take_count.get_RowCount(card_id);
            
            int value_02 = Validation_Tax.tax_validation(card_id);
            
            if(Integer.valueOf(count)== 3)
            {
                jTextField12.setText("Card Unavailable");
            }
            else if(value_02 == 1)
            {
                jTextField12.setText("Card Disable : Payment Request!!");
            }
            else
            {
                jTextField12.setText("Card Available");
            }
    }
    
    public void book_return()
    {
        if(cbox.isSelected())
        {
            Random rand = new Random();

            int random_id = rand.nextInt(99999);

            String ID_ = String.valueOf(random_id);
        
            String return_id = "RE-"+ID_;
            
            Connection connection =  Connector.connection();
                    
            String SQL = "DELETE FROM LEND_TABLE WHERE BOOK_ID = '"+Integer.valueOf(book_id.getText())+"' AND USER_ID = '"+card_id+"';";
            
            String SQL_2 = "INSERT INTO RETURN_TABLE (RETURN_ID,BOOK_ID,USER_ID,TAX,STATUS) VALUES ('"+return_id+"','"+Integer.valueOf(book_id.getText())+"','"+card_id+"','"+tax_data+"','"+status+"');";
            
            try
            {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        
                preparedStatement.execute();
                
                PreparedStatement preparedStatement_03 = connection.prepareStatement(SQL_2);
                
                preparedStatement_03.execute();
                
                done1 box = new done1();
            
                box.setVisible(true);
                
                String template = "<h>\n" +
"    <p><pre>\n" +
"\n" +
"        You Just Return BOOK :"+jTextField9.getText()+" AND YOU HAVE TO PAY LKR."+tax.getText()+" FOR LATE BOOK RETURN FUND.\n" +
"        \n" +
"        Book Care Rules:\n" +
"\n" +
"        1. Keep books clean; make sure hands are clean before touching.\n" +
"\n" +
"        2. Keep books in a safe place away from babies, pets, food, and liquids.\n" +
"\n" +
"        3. Turn pages carefully from the corner and use a bookmark to mark your place. Never write or draw in a\n" +
"        book or ear mark pages.\n" +
"\n" +
"        4. When you are not reading your library books, keep them in your backpack or in your Lecture Hall in Campus.\n" +
"    </pre></p>\n" +
"</h>";
                
                email send = new email(mail,user_name,"Book Return Notification",template,card_id);
            
                send.start();
                
                reload_all();
            }
            catch(Exception Error)
            {
                System.out.println("SQL ERROR : "+Error);
                
                Sql_error error = new Sql_error();
                
                error.setVisible(true);
            }
        }
        else
        {
            Check_Box box = new Check_Box();
            
            box.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        book_id = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        rrdate = new javax.swing.JTextField();
        tax = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cbox = new javax.swing.JCheckBox();
        jTextField12 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        rdate = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        slot_1 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        slot_2 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        slot_3 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel9.setText("jLabel9");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setMinimumSize(new java.awt.Dimension(13605, 666));
        setPreferredSize(new java.awt.Dimension(1365, 666));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 0, 30)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Animations/751-share-outline.gif"))); // NOI18N
        jLabel1.setText("RETRN BOOKS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        book_id.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        book_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        book_id.setBorder(null);
        book_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book_idActionPerformed(evt);
            }
        });
        book_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                book_idKeyReleased(evt);
            }
        });
        getContentPane().add(book_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 70, 380, 44));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 115, 380, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Animations/333-loader-4.gif"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 70, -1, 44));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel6.setText("Fine For Book : ");

        jLabel7.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel7.setText("Selected Book Name : ");

        jLabel10.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel10.setText("Total Fine Count : ");

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        jTextField6.setBorder(null);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        rrdate.setEditable(false);
        rrdate.setBackground(new java.awt.Color(255, 255, 255));
        rrdate.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        rrdate.setBorder(null);

        tax.setEditable(false);
        tax.setBackground(new java.awt.Color(255, 255, 255));
        tax.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        tax.setBorder(null);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dark Icons/return_40px.png"))); // NOI18N
        jLabel4.setText("RETURN BOOK ");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel4KeyPressed(evt);
            }
        });

        cbox.setBackground(new java.awt.Color(255, 255, 255));
        cbox.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        cbox.setText("I Return Selected Book Just Now ");
        cbox.setFocusable(false);
        cbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxActionPerformed(evt);
            }
        });

        jTextField12.setEditable(false);
        jTextField12.setBackground(new java.awt.Color(255, 255, 255));
        jTextField12.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        jTextField12.setBorder(null);

        jLabel14.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel14.setText("Card Status : ");

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel11.setText("Remaning Dates : ");

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        jTextField9.setBorder(null);

        jLabel18.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel18.setText("Due Date :");

        rdate.setEditable(false);
        rdate.setBackground(new java.awt.Color(255, 255, 255));
        rdate.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        rdate.setBorder(null);

        jSeparator18.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator16)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                        .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                        .addComponent(rrdate, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                        .addComponent(tax, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                        .addComponent(jSeparator18)
                                        .addComponent(rdate))
                                    .addComponent(jTextField9)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                    .addComponent(jTextField12))))
                        .addGap(40, 40, 40))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rrdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, 650, 520));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel15.setText("Due Date :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, 175, 39));

        jLabel16.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel16.setText("Lended Book Name : ");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, 185, 39));

        slot_1.setEditable(false);
        slot_1.setBackground(new java.awt.Color(255, 255, 255));
        slot_1.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        slot_1.setBorder(null);
        jPanel3.add(slot_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 67, 312, 39));

        jTextField15.setEditable(false);
        jTextField15.setBackground(new java.awt.Color(255, 255, 255));
        jTextField15.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        jTextField15.setBorder(null);
        jPanel3.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 127, 312, 39));

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 107, 312, 10));

        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 167, 312, 10));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel17.setText("LENDED SLOT 02");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 183, 620, -1));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel20.setText("LENDED SLOT 01");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 620, -1));

        jLabel21.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel21.setText("Lended Book Name : ");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 185, 39));

        jLabel22.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel22.setText("Due Date : ");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 297, 175, 39));

        slot_2.setEditable(false);
        slot_2.setBackground(new java.awt.Color(255, 255, 255));
        slot_2.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        slot_2.setBorder(null);
        slot_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slot_2ActionPerformed(evt);
            }
        });
        jPanel3.add(slot_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 237, 312, 39));

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        jTextField11.setBorder(null);
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 297, 312, 39));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 277, 312, 10));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 337, 312, 10));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dark Icons/ok_30px.png"))); // NOI18N
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 237, -1, 39));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel23.setText("LENDED SLOT 03");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 353, 208, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel5.setText("Lended Book Name : ");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 406, 186, 39));

        jLabel8.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel8.setText("Due Date :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 466, 208, 39));

        slot_3.setEditable(false);
        slot_3.setBackground(new java.awt.Color(255, 255, 255));
        slot_3.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        slot_3.setBorder(null);
        jPanel3.add(slot_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 406, 312, 39));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
        jTextField3.setBorder(null);
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 466, 312, 39));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 51, 620, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 221, 620, 10));

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 620, 10));

        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 446, 312, 10));

        jSeparator17.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 506, 312, 10));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dark Icons/ok_30px.png"))); // NOI18N
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 67, -1, 39));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dark Icons/ok_30px.png"))); // NOI18N
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 406, -1, 39));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 640, 530));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout_rounded_left_30px.png"))); // NOI18N
        jLabel3.setToolTipText("Close Section");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void book_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_book_idActionPerformed

    private void slot_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slot_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slot_2ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void cboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxActionPerformed

    private void book_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_book_idKeyReleased
        
        int length = book_id.getText().length();
        
        if(length == 10)
        {             
             user_data();
            
             jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clear_symbol_35px.png"))); 
        }
        else
        {
             jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Animations/333-loader-4.gif"))); 
        }
        
    }//GEN-LAST:event_book_idKeyReleased

    private void jLabel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel4KeyPressed
        
        
    }//GEN-LAST:event_jLabel4KeyPressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        
        book_return();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
      clear_all();
      
    }//GEN-LAST:event_jLabel2MouseClicked

    public void clear_all()
    {               
       jTextField9.setText("");
       
       rdate.setText("");
       
       rrdate.setText("");
              
       tax.setText("");
       
       book_id.setText("");
    }
    
    
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
         new close().setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

     public void load_lending_table() throws SQLException
    {
        Integer ID = card_id;
        
        book.clear();
        
        Connection connection =  Connector.connection();
        
        String SQL = "SELECT * FROM LEND_TABLE WHERE USER_ID = '"+ID+"'";
        
        int count = 0;
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                rs.getString("BOOK_ID");
                
                book.add(rs.getString("BOOK_ID")); 
                
                System.out.println(rs.getString("BOOK_ID"));
                
                count = count + 1;
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }
        
            System.out.println(book);
            
            book_nameLoad();

    }
    public void book_nameLoad()
    {
        Connection connection =  Connector.connection();
        
        int array_size = book.size();
        
        if(array_size == 1)
        {
            String SQL = "SELECT * FROM BOOKS WHERE BOOK_ID ='"+book.get(0)+"'; ";
            
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                slot_1.setText(rs.getString("TITLE_OF_BOOK"));
                
                System.out.println(rs.getString("TITLE_OF_BOOK"));
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }
            
            
        }
        if(array_size == 2)
        {
        String SQL = "SELECT * FROM BOOKS WHERE BOOK_ID ='"+book.get(0)+"'; ";
        
        String SQL_02 = "SELECT * FROM BOOKS WHERE BOOK_ID ='"+book.get(1)+"'; ";
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                slot_1.setText(rs.getString("TITLE_OF_BOOK"));
                
                System.out.println(rs.getString("TITLE_OF_BOOK"));
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL_02);
            
            while(rs.next())
            {

                slot_2.setText(rs.getString("TITLE_OF_BOOK"));
                
                System.out.println(rs.getString("TITLE_OF_BOOK"));
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }        
                
        }
        
        if(array_size == 3)
        {
        String SQL = "SELECT * FROM BOOKS WHERE BOOK_ID ='"+book.get(0)+"'; ";
        
        String SQL_02 = "SELECT * FROM BOOKS WHERE BOOK_ID ='"+book.get(1)+"'; ";
         
        String SQL_03 = "SELECT * FROM BOOKS WHERE BOOK_ID ='"+book.get(2)+"'; ";
        
                try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                slot_1.setText(rs.getString("TITLE_OF_BOOK"));
                
                System.out.println(rs.getString("TITLE_OF_BOOK"));
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL_02);
            
            while(rs.next())
            {

                slot_2.setText(rs.getString("TITLE_OF_BOOK"));
                
                System.out.println(rs.getString("TITLE_OF_BOOK"));
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }        
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL_03);
            
            while(rs.next())
            {
                slot_3.setText(rs.getString("TITLE_OF_BOOK"));
                                
                System.out.println(rs.getString("TITLE_OF_BOOK"));
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }
        
        }
        
    }
    
    public void load_time() throws SQLException
    {
        int size = book.size();
        
        if(size == 1)
        {
             jTextField15.setText(date.load_dates(Integer.valueOf(book.get(0))));
        }
        if(size == 2)
        {
            jTextField15.setText(date.load_dates(Integer.valueOf(book.get(0))));
            
            jTextField11.setText(date.load_dates(Integer.valueOf(book.get(1))));       
        }
        if(size == 3)
        {
            jTextField15.setText(date.load_dates(Integer.valueOf(book.get(0))));
            
            jTextField11.setText(date.load_dates(Integer.valueOf(book.get(1))));
             
            jTextField3.setText(date.load_dates(Integer.valueOf(book.get(2))));           
        }
    }
    
    public void user_data()
    {
        int get_id = Integer.valueOf(book_id.getText());
        
        int data = Validation_Bookowner.book_data_load(get_id,card_id);
        
        if(data == 1)
        {
            jTextField9.setText(load_bookname.book_data_load(get_id));
            
            lending_table();
        }
        else
        {
            Error error = new Error();
            
            error.setVisible(true);
            
            book_id.setText("");
        }  
    }
        
    public void lending_table()
    {
        Connection connection = Connector.connection();
        
        int get_id = Integer.valueOf(book_id.getText());
        
        String SQL = "SELECT * FROM LEND_TABLE WHERE BOOK_ID = "+get_id+" ";
        
        int book_id = 0;
        
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                book_id = rs.getInt("BOOK_ID");
                
                System.out.println(book_id);
                
                rdate.setText(rs.getString("DUE_DATE"));
                
                int days = time_management.time_management(rs.getString("DUE_DATE"));
                
                String value = String.valueOf(Math.abs(days));
                
                if(days == 0)
                {
                    rrdate.setText("No More Days Left");
                    
                    tax.setText("LKR 0.00");
                    
                    status = 1;
                }
                else if(days > 0)
                {
                    rrdate.setText(value+" Days Available");
                    
                    tax.setText("LKR 0.00");
                    
                    status = 1;
                }
                else if(days < 0)
                {
                    rrdate.setText(value+" Days Late");
                    
                    int tax_ = Math.abs(days);
                    
                    Integer db_value = Integer.valueOf(Load_Settings.load_settings("LPAYMENT"));
                    
                    int calculation = tax_ * db_value;
                    
                    tax.setText(String.valueOf("LKR "+calculation+".00")); 
                    
                    status = 0;
                    
                    tax_data = calculation;
                }
            }
            
            pin_operation();
        }
        catch(SQLException ERROR)
        {
            System.out.println("ERROR : "+ERROR);
        }
    }
    
    
    public void pin_operation()
    {
        String book_name = jTextField9.getText();
        
        if(book_name.equals(slot_1.getText()))
        {
            jLabel13.setVisible(true);
        }
        else if(book_name.equals(slot_2.getText()))
        {
            jLabel25.setVisible(true);
        }
        else if(book_name.equals(slot_3.getText()))
        {
            jLabel12.setVisible(true);
        }
    }

 
    public void get_mailAddress()
    {
        Connection connection =  Connector.connection();
        
        String SQL = "SELECT * FROM LIBRARY_USER WHERE USER_ID = '"+card_id+"'";
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {   
                
                mail= rs.getString("EMAIL_ADDRESS");
                
                user_name= rs.getString("USER_NAME");
            }
        }
        catch(Exception ERROR)
        {
          System.out.println("ERROR : "+ERROR);  
        }        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField book_id;
    private javax.swing.JCheckBox cbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField rdate;
    private javax.swing.JTextField rrdate;
    private javax.swing.JTextField slot_1;
    private javax.swing.JTextField slot_2;
    private javax.swing.JTextField slot_3;
    private javax.swing.JTextField tax;
    // End of variables declaration//GEN-END:variables
}
