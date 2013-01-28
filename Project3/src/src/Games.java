package src;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author jonathan
 */
public class Games extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String Email;
    String userID;
    String userID2;
    int pl2;
    int score1;
    int score2;
    String Gameid;
    String s1;
    String s2;
    
    public Games(String Email) {
        this.Email = Email;
        initComponents();
        conn=connect.connect();
        GetUser();
        update_text();
        Update_table();
        
    }
    private void GetUser(){
        try{
           String sql ="select ID from Facebook, Users WHERE Facebook.Email = '"+Email+"'  AND Facebook.FacebookId = Users.u_FacebookId;";
           ps = conn.prepareStatement(sql);
           rs = ps.executeQuery();
           if(rs.next()){
               userID = rs.getString("ID");
               
           }   
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }finally{ //needed to close all the connections so you wont have a database lock
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
        }
    }
    private void update_text(){
        try{
            String sql = "SELECT Facebook.Name,Users.tokens  from Facebook, Users where Email = '"+Email+"' and Users.u_FacebookId = Facebook.FacebookId;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
              
               String i = rs.getString("Name");
               name_txt.setText(i);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{//needed to close all the connections so you wont have a database lock
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
        }
        
    }
    private void Update_table(){
       try{
        
        String sql = "SELECT distinct f1.Name as Player1, f2.Name as Player2, p1.score as Player1_Score,p2.score as Player2_Score from Player as p1 , Player as p2, Game, Users as u1, Users as u2, Facebook as f1, Facebook as f2 where p1.p_userid = '"+ userID +"' and p1.p_gameID = p2.p_gameID and p1.p_userid <> p2.p_userid and p1.p_gameID = Game.gameId and p2.p_gameID = Game.gameId and p1.p_userid = u1.ID and p2.p_userid = u2.ID and u1.u_FacebookId = f1.FacebookId and u2.u_FacebookId = f2.FacebookId;";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        game_table.setModel(DbUtils.resultSetToTableModel(rs));
       
       
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally{//needed to close all the connections so you wont have a database lock
           try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
       } 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        game_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        newgame_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        name_txt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        game_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Player 1", "Player 2", "Player 1 Score", "Player 2 Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(game_table);

        jLabel1.setText("Game information:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        newgame_button.setText("New Game");
        newgame_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newgame_buttonActionPerformed(evt);
            }
        });

        jButton2.setText("<< Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Name:");

        name_txt.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        jLabel3.setText("Games");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newgame_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(name_txt))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(name_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newgame_button)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
                rs.close(); //needed to close all the connections so you wont have a database lock
                ps.close(); }
            catch(Exception e) { } 
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void gamedata(){
       score1 = 0 + (int) ( Math.random()*(100 - 0) );
       score2 = 0 + (int) ( Math.random()*(100 - 0) );
       String sql = "Select max(ID) from Users;";
      try{
       ps = conn.prepareStatement(sql);
       rs = ps.executeQuery();
       if(rs.next()){
           String player = rs.getString("max(ID)");
           int player2 = Integer.parseInt(player);
           pl2 = 0 + (int) ( Math.random()*(player2 - 0) ); 
       }
      }
      catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally{//needed to close all the connections so you wont have a database lock
           try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
       } 
    }
    private void gameID(){
        String sql = "Select max(gameId)+1 from Game";
        try{
            ps= conn.prepareStatement(sql);
            rs= ps.executeQuery();
            Gameid = rs.getString("max(gameId)+1");
            
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally{//needed to close all the connections so you wont have a database lock
           try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
       }
    }
    private void updategame(){
        String sql = "insert into Game values(?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            userID2 = Integer.toString(pl2);
            ps.setString(1, userID);
            ps.setString(2, Gameid);
            ps.setString(3, userID2);
            ps.executeUpdate();
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally{//needed to close all the connections so you wont have a database lock
           try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
       }
    }
    private void updateplayers(){
        String sql = "insert into player values(?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            s1 = Integer.toString(score1);
            ps.setString(1, Gameid);
            ps.setString(2, s1);
            ps.setString(3, userID);
            ps.executeUpdate();
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally{//needed to close all the connections so you wont have a database lock
           try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
       }
        sql = "insert into player values(?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            s2 = Integer.toString(score2);
            ps.setString(1, Gameid);
            ps.setString(2, s2);
            ps.setString(3, userID2);
            ps.executeUpdate();
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally{//needed to close all the connections so you wont have a database lock
           try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
       }
    }
    private void newgame_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newgame_buttonActionPerformed
       gamedata();
       gameID();
       updategame();
       updateplayers();
       Update_table();
    }//GEN-LAST:event_newgame_buttonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Games.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Games.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Games.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Games.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Games().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable game_table;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name_txt;
    private javax.swing.JButton newgame_button;
    // End of variables declaration//GEN-END:variables
}
