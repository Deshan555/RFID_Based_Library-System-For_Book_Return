/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import SQL_Connector.Connector;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * 
 * @author Jayashanka Deshan
 * 1360, 688
 */
public class menu_1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form menu
     */
    
    ArrayList<String> data_list = new ArrayList<String>();
    
    load_thread obj_ = new load_thread();
    
    public menu_1()
    {
        initComponents();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        BasicInternalFrameUI bis = (BasicInternalFrameUI) this.getUI();
        
        bis.setNorthPane(null);
        
        table.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        
        table.setBackground(new Color(255,255,255));
        
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        
        table.getTableHeader().setOpaque(false);
        
        table.getTableHeader().setBackground(Color.white);
                
        table.setEditingColumn(0);
        
        table.setEditingRow(0);
        
        get_category();
        
        jLabel12.setVisible(false);
        
        obj_.start();
       
    }
    
    public void book_data_load(String ID)
    {
        Connection connection = Connector.connection();
        
        Integer book_id = Integer.valueOf(ID);
        
        String SQL= "SELECT * FROM BOOKS WHERE BOOK_ID = '"+book_id+"';";     
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
                name.setText(rs.getString("TITLE_OF_BOOK"));
                                
                author.setText(rs.getString("AUTHOR_NAME"));
                                
                cat.setText(rs.getString("CATEGORY"));
                
                section.setText(rs.getString("SECTION"));
                
                String link = rs.getString("BOOK_LINK");
                
                url.setText(rs.getString("BOOK_LINK"));
                
                String Book_id = rs.getString("BOOK_ID");
                
                load_lendData(Book_id);
                
                jTextField8.setText(Book_id);
                
                if(url.getText().trim().isEmpty())
                {
                   url.setText("No Book Link Found");
                   
                   jLabel12.setVisible(false);
                }
                else
                {
                    jLabel12.setVisible(true);
                    
                    jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/send_letter_30px.png"))); 
                }
                
                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animations/approve.gif")));
                
            }
        }
        catch(Exception ERROR)
        {
            System.out.println(ERROR);
        }
    }
    
    public void row_empty()
    {
        name.setText("");
                                
        author.setText("");
                                
        cat.setText("");
                
        section.setText("");
                                
        url.setText("");
        
        owner.setText("");
        
        rdate.setText("");
        
        jLabel12.setVisible(false);
        
        jTextField8.setText("");
        
    }
    
    public void search()
    {
        if(box.getText().trim().isEmpty())
        {
            obj_.start();
            
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animations/333-loader-4.gif")));
        }
        
        row_empty();
        
        if(choose.getSelectedItem().equals("Search By Author Name"))
        {
            String SQL= "SELECT BOOK_ID,TITLE_OF_BOOK,AUTHOR_NAME,ISBN FROM BOOKS WHERE AUTHOR_NAME LIKE '"+box.getText()+"%';";
            
            data_load(SQL);
        }
        else if(choose.getSelectedItem().equals("Search By Book Name"))
        {
            String SQL= "SELECT BOOK_ID,TITLE_OF_BOOK,AUTHOR_NAME,ISBN FROM BOOKS WHERE TITLE_OF_BOOK LIKE '"+box.getText()+"%';";
            
            data_load(SQL);
        }
        else if(choose.getSelectedItem().equals("Search By Section"))
        {
            String SQL= "SELECT BOOK_ID,TITLE_OF_BOOK,AUTHOR_NAME,ISBN FROM BOOKS WHERE SECTION = '"+box.getText()+"';";
            
            data_load(SQL);
        }
        else if(choose.getSelectedItem().equals("Search By Category"))
        {
            String SQL= "SELECT BOOK_ID,TITLE_OF_BOOK,AUTHOR_NAME,ISBN FROM BOOKS WHERE TITLE_OF_BOOK LIKE '"+box.getText()+"%' AND CATEGORY = '"+items.getSelectedItem()+"';"; 
            
            data_load(SQL);
        }
    }
    
    
    public void data_load(String SQL)
    {
        Connection connection = Connector.connection();
        
       try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(SQLException ERROR)
        {
            System.out.println(ERROR);
        }    
    }
    
    public void get_category()
    {
       String SQL = "SELECT DISTINCT CATEGORY FROM BOOKS ;";
        
       Connection connection = Connector.connection();
        
       try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            while(rs.next())
            {
               data_list.add(rs.getString("CATEGORY"));
            }
 
        }
        catch(SQLException ERROR)
        {
            System.out.println(ERROR);
        }    
        
       load_category();
    }
    
    public void load_category()
    {
        int size = data_list.size();
        
        for(int i = 0;i < size;i++)
        {
            items.addItem(data_list.get(i));
        }
    }
    
    public void load_lendData(String book_id)
    {
        Connection connection = Connector.connection();
        
        Integer book_idn = Integer.valueOf(book_id);
        
        String SQL = "SELECT * FROM LEND_TABLE WHERE BOOK_ID = "+book_idn+";";
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            Integer ID = 0;
            
            while(rs.next())
            {

               ID = Integer.valueOf(rs.getString("USER_ID"));
                              
               rdate.setText(rs.getString("DUE_DATE"));
                    
               load_userData(ID);
               
               System.out.println(ID);
            }
        }
        catch(SQLException ERROR)
        {
            System.out.println(ERROR);  
        }
    }
    
    public void load_userData(int id)
    {
        Connection connection = Connector.connection();
        
        String SQL = "SELECT * FROM LIBRARY_USER WHERE USER_ID = "+id+";";
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            Integer ID = 0;
            
            while(rs.next())
            {
                owner.setText(rs.getString("USER_NAME"));
            }
        }
        catch(SQLException ERROR)
        {
            System.out.println(ERROR);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        box = new javax.swing.JTextField();
        items = new javax.swing.JComboBox<>();
        choose = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        author = new javax.swing.JLabel();
        cat = new javax.swing.JLabel();
        url = new javax.swing.JLabel();
        section = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JLabel();
        owner = new javax.swing.JLabel();
        rdate = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setMinimumSize(new java.awt.Dimension(13605, 666));
        setPreferredSize(new java.awt.Dimension(1365, 666));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 30)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Animations/112-book-morph-outline (1).gif"))); // NOI18N
        jLabel1.setText("FIND BOOKS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1, 259, -1));

        box.setFont(new java.awt.Font("Yu Gothic Medium", 0, 17)); // NOI18N
        box.setBorder(null);
        box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                boxKeyReleased(evt);
            }
        });
        getContentPane().add(box, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 76, 368, 42));

        items.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
        items.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        items.setFocusable(false);
        getContentPane().add(items, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 80, 191, -1));

        choose.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
        choose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By Book Name", "Search By Section", "Search By Author Name", "Search By Category" }));
        choose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        choose.setFocusable(false);
        choose.setOpaque(false);
        getContentPane().add(choose, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 80, 200, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 119, 368, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Animations/333-loader-4.gif"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 80, -1, 30));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(204, 204, 204));

        table.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )

        {
            public boolean isCellEditable(int rowIndex,int colIndex)
            {
                return false;
            }
        }

    );
    table.setFocusable(false);
    table.setGridColor(new java.awt.Color(204, 204, 204));
    table.setOpaque(false);
    table.setRowHeight(25);
    table.setSelectionBackground(new java.awt.Color(142, 234, 195));
    table.setSelectionForeground(new java.awt.Color(102, 102, 102));
    table.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(table);

    getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(652, 150, 710, 510));

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));
    jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel3.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel3.setText("Category :");
    jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 132, 47));

    jLabel4.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel4.setText("Author : ");
    jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 132, 47));

    jLabel5.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel5.setText("Book Name : ");
    jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 132, 47));

    jLabel6.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel6.setText("Current Owner :");
    jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 367, 132, 47));

    jLabel7.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel7.setText("Book ID :");
    jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 308, 132, 47));

    jLabel9.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel9.setText("Section : ");
    jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 249, 132, 47));

    jLabel10.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel10.setText("Book Link : ");
    jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 132, 47));

    jLabel11.setFont(new java.awt.Font("Yu Gothic", 0, 18)); // NOI18N
    jLabel11.setText("Return Date : ");
    jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 426, 132, 47));

    jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 59, 374, 10));

    jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 118, 374, 10));

    jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 177, 374, 10));

    jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 238, 374, 10));

    jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 297, 374, 10));

    jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 356, 374, 10));

    jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 415, 374, 10));

    jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
    jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 474, 374, 10));

    jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/send_letter_30px.png"))); // NOI18N
    jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel12MouseClicked(evt);
        }
    });
    jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, 30));

    name.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 370, 50));

    author.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(author, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 370, 40));

    cat.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 370, 50));

    url.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(url, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 370, 50));

    section.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(section, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 370, 50));

    jTextField8.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 370, 40));

    owner.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(owner, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 370, 50));

    rdate.setFont(new java.awt.Font("Yu Gothic", 0, 16)); // NOI18N
    jPanel1.add(rdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 370, 40));

    getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 650, 510));

    jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout_rounded_left_30px.png"))); // NOI18N
    jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel13MouseClicked(evt);
        }
    });
    getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 10, -1, 30));

    jLabel8.setBackground(new java.awt.Color(255, 255, 255));
    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Background_01.png"))); // NOI18N
    getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 150));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        
        row_empty();
        
        int row = table.getSelectedRow();
        
        String ID = table.getValueAt(row,0).toString();
        
        System.out.println(ID);
                
        book_data_load(ID);
    }//GEN-LAST:event_tableMouseClicked

    private void boxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxKeyReleased
        
        search();
    }//GEN-LAST:event_boxKeyReleased

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        
        new mail(name.getText(),author.getText(),cat.getText(),url.getText()).setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        
         new close().setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    public class load_thread implements Runnable
    {
    private final Thread thread;
    
    public load_thread() 
    {
        thread = new Thread(this);
    }
        
        @Override
        public void run() 
        {
        Connection connection = Connector.connection();
        
        String SQL = "SELECT BOOK_ID,TITLE_OF_BOOK,AUTHOR_NAME FROM BOOKS";
        
        try
        {
            Statement stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery(SQL);
            
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ERROR)
        {
            System.out.println(ERROR);
        }
        }
        
        public void start()
        {
            thread.start();
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JTextField box;
    private javax.swing.JLabel cat;
    private javax.swing.JComboBox<String> choose;
    private javax.swing.JComboBox<String> items;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel jTextField8;
    private javax.swing.JLabel name;
    private javax.swing.JLabel owner;
    private javax.swing.JLabel rdate;
    private javax.swing.JLabel section;
    private javax.swing.JTable table;
    private javax.swing.JLabel url;
    // End of variables declaration//GEN-END:variables
}
