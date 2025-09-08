# Test cases
- Sätt en nod till ett värde och testa att get värdet från indexet
- Lägg till på ett värde på ett index som är större än vad trädet just nu klarar av så att trädet behöver bli större. Testa att get något av de äldre värdena och det nya.
- Testa att get från index som inte har något värde än och som är större än vad trädet just nu klarar av.
- Testa att ändra värdet på en nod och se om värdet har ändrats.
- Unset efter att vi ändrat ett värde och ser om noden går tillbaka till sitt ursprungliga värde.
- Vi skapade en funktion printLeaves som printade ut värdet på alla löv. Denna användes för att se att alla värden förändrades efter en unset.
- Vi använde printleaves för att se att set inte ändrade på något värde förutom det som skulle ändras.
- Vi gjorde unset tills vi kom tillbaka till när trädet skapades och såg till så att inget oväntat hände.