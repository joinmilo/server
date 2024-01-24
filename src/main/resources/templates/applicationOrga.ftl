  <!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>

<body style=" margin: 0px; word-wrap: break-word;">
  <div
    style="border: 0.3rem solid #E5E5E5; background-color: #E5E5E5; padding: 5rem 0; font-family: 'Roboto'; font-style: normal; font-weight: 400; text-align: center; height: 100%">
    <div style="margin-bottom: 3rem;">
      <strong style="color: gray; font-size: x-large;">${portalName}</strong>
    </div>
    <div style="background-color: white; border-radius: 16px 16px 16px 16px; box-shadow: 0px 2px 8px 0px #00000026;">
      <div style="padding: 1rem; background-color: white; border-radius: 16px 16px 16px 16px;">
        <!-- <div style="background: radial-gradient(#D16C40, #934524) ; height: 16rem; width: 100%; border-radius: 16px 16px 0 0;"></div> -->
		<p>Hallo${userName},</p>
		
		<p>der Benutzer mit der Email Adresse ${newMemberEmail} möchte Mitglied der Organisation "${orgaName}" werden.<br>
		Melde dich bitte im ${portalName} an und bestätigen oder lösche die Anfrage.</p>
		
		<div style="margin-top: 2rem; padding: 1rem;">
			<a href="${link}" style="text-decoration: none; color: white; text-align: center; padding: 1rem 2rem; background-color: #247294; border-radius: 1.5rem; width: max-content;">
				<span>
					Hier gehts zum Portal
          		</span>
        	</a>
      	</div>
		
		<p>Dies ist eine automatisch generierte Mail. Bitte antworte nicht darauf.</p>
      <div style="padding: 2rem 1rem">
        <span>
          Dein ${portalName}-Team
        </span>
      </div>
    </div>
  </div>
</body>
</html>