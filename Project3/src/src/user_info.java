
package src;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author jonathan
 */
public class user_info extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String Email;


    public user_info(String Email) {
        this.Email = Email;
        initComponents();
        conn=connect.connect();
        Update_table();
        Update_name();
    }
    public void Getting_email(String Email){
        this.Email = Email;
        
    }
    private void Update_name(){
        try{
            String sql = "SELECT Facebook.Name,Users.tokens, Region.name as rname  from Facebook, Users, Region where Email = '"+Email+"' and Users.u_FacebookId = Facebook.FacebookId and Users.u_rkey = Region.r_key;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
               email_txt.setText(Email);
               String i = rs.getString("Name");
               name_field.setText(i);
               String x = rs.getString("tokens");
               coins_txt.setText(x);
               String y = rs.getString("rname");
               region_txt.setText(y);
            }
            
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } } 
        
    }
    private void Update_table(){
       try{
        
        String sql = "SELECT Facebook.Email, Facebook.Name FROM Users, Facebook, (SELECT DISTINCT f_userid1 FROM Friends WHERE f_userid2 = (select Users.ID from Users, Facebook where Facebook.Email = '"+Email+"' and Users.u_FacebookId = Facebook.FacebookId) OR f_userid1 IN (SELECT f_userid2 FROM friends WHERE f_userid1 = (select Users.ID from Users, Facebook where Facebook.Email = '"+Email+"' and Users.u_FacebookId = Facebook.FacebookId)))fID WHERE Users.ID = fID.f_userid1 AND Users.u_FacebookId = Facebook.FacebookId";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        Friends_List.setModel(DbUtils.resultSetToTableModel(rs));
       
       
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Update_button = new javax.swing.JButton();
        games_button = new javax.swing.JButton();
        EditFriends_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        email_txt = new javax.swing.JLabel();
        name_field = new javax.swing.JLabel();
        name_txt = new javax.swing.JLabel();
        User_name = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        region_txt = new javax.swing.JLabel();
        coins_txt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        buy_coins = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Friends_List = new javax.swing.JTable();
        logout_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Update_button.setText("Update");
        Update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_buttonActionPerformed(evt);
            }
        });

        games_button.setText("Games");
        games_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                games_buttonActionPerformed(evt);
            }
        });

        EditFriends_button.setText("Edit Friends");
        EditFriends_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditFriends_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Update_button, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(games_button, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EditFriends_button, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Update_button)
                    .addComponent(games_button)
                    .addComponent(EditFriends_button)))
        );

        email_txt.setText("jLabel1");

        name_field.setText("jLabel1");

        name_txt.setText("Name:");

        User_name.setText("Email:");

        jLabel4.setText("Region:");

        region_txt.setText("jLabel5");

        coins_txt.setText("jLabel3");

        jLabel1.setText("Coins:");

        buy_coins.setText("Buy Coins");
        buy_coins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buy_coinsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(name_txt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(name_field))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(User_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(email_txt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coins_txt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buy_coins))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(region_txt)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_txt)
                    .addComponent(name_field)
                    .addComponent(jLabel4)
                    .addComponent(region_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(coins_txt)
                        .addComponent(buy_coins, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(User_name)
                        .addComponent(email_txt)))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        jLabel3.setText("User Information");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Friends list");

        Friends_List.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Friends_List.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Email"
            }
        ));
        jScrollPane1.setViewportView(Friends_List);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(364, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        logout_button.setText("Log out");
        logout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                        .addComponent(logout_button, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(logout_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void games_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_games_buttonActionPerformed
              try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
               Games x = new Games(Email);
               x.setVisible(true);
    }//GEN-LAST:event_games_buttonActionPerformed

    private void buy_coinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buy_coinsActionPerformed
        try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
        buy x = new buy(Email);
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buy_coinsActionPerformed

    private void logout_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_buttonActionPerformed
        try{
                rs.close(); 
                ps.close(); }
        catch(Exception e) { } 
        login x = new login();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logout_buttonActionPerformed

    private void Update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_buttonActionPerformed
        try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
        ProfileEditor x = new ProfileEditor(Email);
        x.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_Update_buttonActionPerformed

    private void EditFriends_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditFriends_buttonActionPerformed
         try{
                rs.close(); 
                ps.close(); 
     }
            catch(Exception e) { } 
               FriendEditor x = new FriendEditor(Email);
               x.setVisible(true);     
               this.dispose();
    }//GEN-LAST:event_EditFriends_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(user_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new user_info().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditFriends_button;
    private javax.swing.JTable Friends_List;
    private javax.swing.JButton Update_button;
    private javax.swing.JLabel User_name;
    private javax.swing.JButton buy_coins;
    private javax.swing.JLabel coins_txt;
    private javax.swing.JLabel email_txt;
    private javax.swing.JButton games_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout_button;
    private javax.swing.JLabel name_field;
    private javax.swing.JLabel name_txt;
    private javax.swing.JLabel region_txt;
    // End of variables declaration//GEN-END:variables
}
