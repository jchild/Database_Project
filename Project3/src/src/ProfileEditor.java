
package src;

/**
 *
 * @author kevin
 */
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.*;

public class ProfileEditor extends javax.swing.JFrame {


    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String Email;
    String Name;
    String nEmail;
    String nName;
    String nPassword;
    String nPassword2;
    String regionName;
    
    
     public ProfileEditor(String Email) {
        this.Email = Email;
        conn=connect.connect();
        initComponents();
        Update_fields();
        Update_combobox();
    }
  private void Update_fields(){
       email_text.setText(Email);
      try{
            String sql = "select name  from facebook where email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,Email);
            rs = ps.executeQuery();
            if(rs.next()){
               Name = rs.getString("Name");
               name_text.setText(Name);
            }
            
            
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       } finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } }
        
       
    }
  private void Update_combobox(){
      try{
       String sql ="select name from Region;";
       ps = conn.prepareStatement(sql);
       rs=ps.executeQuery();
     
      
       region_scrollbar.removeAllItems();
       while (rs.next())
         {
            region_scrollbar.addItem(rs.getString("Name"));
           
        }

      }
      catch (Exception e)
      {
          JOptionPane.showMessageDialog(null,e + ": Error in Update_combobox");

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
        email_text = new javax.swing.JTextField();
        name = new javax.swing.JLabel();
        password2_text = new javax.swing.JPasswordField();
        name_text = new javax.swing.JTextField();
        password1_text = new javax.swing.JPasswordField();
        password2 = new javax.swing.JLabel();
        password1 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        region_scrollbar = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cancel_button = new javax.swing.JButton();
        save_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        email_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_textActionPerformed(evt);
            }
        });
        email_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                email_textKeyPressed(evt);
            }
        });

        name.setText("Name");

        password2_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password2_textKeyPressed(evt);
            }
        });

        name_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_textActionPerformed(evt);
            }
        });
        name_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                name_textKeyPressed(evt);
            }
        });

        password1_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password1_textKeyPressed(evt);
            }
        });

        password2.setText("retype password");

        password1.setText("new password");

        email.setText("e-mail");

        region_scrollbar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        region_scrollbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                region_scrollbarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(name)
                        .addGap(18, 18, 18)
                        .addComponent(name_text))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(email)
                        .addGap(18, 18, 18)
                        .addComponent(email_text))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(region_scrollbar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(password1)
                        .addGap(18, 18, 18)
                        .addComponent(password1_text))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(password2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password2_text)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name)
                    .addComponent(name_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email)
                    .addComponent(email_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password1)
                    .addComponent(password1_text, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(password2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password2_text, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addComponent(region_scrollbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        jLabel3.setText("Update User");

        cancel_button.setText("Cancel");
        cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_buttonActionPerformed(evt);
            }
        });

        save_button.setText("Save");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/update_icon.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(cancel_button, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(save_button)
                            .addComponent(cancel_button))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void region_scrollbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_region_scrollbarActionPerformed

        regionName = (String)region_scrollbar.getSelectedItem();
    }//GEN-LAST:event_region_scrollbarActionPerformed

    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
        try{
            nEmail = email_text.getText();
            nName = name_text.getText();
            nPassword = password1_text.getText();
            nPassword2 = password2_text.getText();
            if (nPassword.equals(nPassword2))
            {

                JOptionPane.showMessageDialog(null,"Passwords match");

                String sql = "update facebook set email=?, name=?, password=? where email=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,nEmail);
                ps.setString(2,nName);
                ps.setString(3,nPassword);
                ps.setString(4,Email);
                ps.execute();
                rs.close();
                ps.close();

                sql ="select r_key from region where name = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,regionName);
                rs=ps.executeQuery();
                String rkey = rs.getString("r_key");
                rs.close();
                ps.close();

                sql = "select FacebookId from Facebook where email = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,nEmail);
                rs = ps.executeQuery();
                String facebookId = rs.getString("FacebookId");
                rs.close();
                ps.close();

                sql = "update users set u_rkey = ? where u_FacebookId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, rkey);
                ps.setString(2,facebookId);
                ps.execute();
                rs.close();
                ps.close();
                this.dispose();
                user_info x = new user_info(nEmail);
                x.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Passwords do not match");
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } }
    }//GEN-LAST:event_save_buttonActionPerformed

    private void cancel_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_buttonActionPerformed
        try{

            this.dispose();
            user_info x = new user_info(Email);
            x.setVisible(true);
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null,e + ": Error within cancel_button()");
        }finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } }
    }//GEN-LAST:event_cancel_buttonActionPerformed

    private void password1_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password1_textKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
            this.dispose();
            user_info x = new user_info(Email);
            x.setVisible(true);
        }  // TODO add your handling code here:
    }//GEN-LAST:event_password1_textKeyPressed

    private void name_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_name_textKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
            this.dispose();
            user_info x = new user_info(Email);
            x.setVisible(true);
        }
    }//GEN-LAST:event_name_textKeyPressed

    private void name_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_textActionPerformed
        
    }//GEN-LAST:event_name_textActionPerformed

    private void password2_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password2_textKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
            this.dispose();
            user_info x = new user_info(Email);
            x.setVisible(true);
        }
    }//GEN-LAST:event_password2_textKeyPressed

    private void email_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_email_textKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } 
            this.dispose();
            user_info x = new user_info(Email);
            x.setVisible(true);
        }
    }//GEN-LAST:event_email_textKeyPressed

    private void email_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_textActionPerformed

    }//GEN-LAST:event_email_textActionPerformed

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
            java.util.logging.Logger.getLogger(ProfileEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfileEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfileEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfileEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton cancel_button;
    private javax.swing.JLabel email;
    private javax.swing.JTextField email_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel name;
    private javax.swing.JTextField name_text;
    private javax.swing.JLabel password1;
    private javax.swing.JPasswordField password1_text;
    private javax.swing.JLabel password2;
    private javax.swing.JPasswordField password2_text;
    private javax.swing.JComboBox region_scrollbar;
    private javax.swing.JButton save_button;
    // End of variables declaration//GEN-END:variables
}
